package dat.backend.model.services;


import dat.backend.model.entities.CompleteProduct;
import dat.backend.model.entities.Orders;
import dat.backend.model.entities.ProductVariant;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.ItemListFacade;
import dat.backend.model.persistence.OrderFacade;
import dat.backend.model.persistence.ProductFacade;
import org.abstractica.javacsg.Geometry3D;
import org.abstractica.javacsg.JavaCSG;
import org.abstractica.javacsg.JavaCSGFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ThreeDBuilder
{
    private static JavaCSG csg;
    public static byte[] getModel(int width, int length, List<CompleteProduct> productList, ConnectionPool connectionPool) throws IOException, DatabaseException
    {
        csg = JavaCSGFactory.createDefault();
        Path path = Paths.get( "deleteme.stl");
        String file = path.toString();
        Geometry3D carport = makeCarport(width, length, productList, connectionPool);
        csg.saveSTL(file, carport);
        byte[] data = Files.readAllBytes(path);
        Files.deleteIfExists(path);
        return data;
    }

    private static Geometry3D makeCarport(int carportWidth, int carportLength, List<CompleteProduct> productList, ConnectionPool connectionPool) throws DatabaseException
    {
        ProductVariant postVariant = null;
        ProductVariant strapVariant = null;
        ProductVariant sternFNBVariant = null;
        ProductVariant sternSideVariant = null;
        ProductVariant raftersVariant = null;
        int postAmount = 0;
        int raftersAmount = 0;


        for (CompleteProduct p:productList) {
            switch (p.getProductId()){
                case 1: // stern front & back id
                    sternFNBVariant = ProductFacade.getProductVariantById(p.getProductVariantId(), connectionPool);
                    break;
                case 3: // stern side id
                    sternSideVariant = ProductFacade.getProductVariantById(p.getProductVariantId(), connectionPool);
                    break;
                case 4: // post id
                    postVariant = ProductFacade.getProductVariantById(p.getProductVariantId(), connectionPool);
                    postAmount = p.getAmount();
                    break;
                case 5: // strap id
                    strapVariant = ProductFacade.getProductVariantById(p.getProductVariantId(), connectionPool);
                    break;
                case 6: // rafters id
                    raftersVariant = ProductFacade.getProductVariantById(p.getProductVariantId(), connectionPool);
                    raftersAmount = p.getAmount();
                    break;
            }
        }
        Geometry3D carport = createBox(0,0,0);
        Geometry3D posts = makePosts(carportWidth, carportLength, strapVariant.getHeight(), postVariant, postAmount);
        Geometry3D straps = makeStraps(carportWidth,carportLength, raftersVariant.getWidth(), strapVariant);
        Geometry3D sternsFrontAndBack = makeSternsFNB(carportWidth, carportLength, sternFNBVariant);
        Geometry3D sternsSide = makeSternsSide(carportWidth, carportLength, sternSideVariant);
        Geometry3D rafters = makeRafters(carportWidth, carportLength, raftersVariant, raftersAmount);
        Geometry3D roof = makeRoof(carportWidth, carportLength);
        carport = csg.union3D(rafters, roof,sternsSide,sternsFrontAndBack,straps, posts);
        return carport;
    }

    private static Geometry3D makeRoof(int carportWidth, int carportLength)
    {
        return csg.translate3D(0,carportLength/2f,300).transform(createBox(carportWidth, carportLength, 3));
    }

    private static Geometry3D makeRafters(int carportWidth, int carportLength, ProductVariant raftersVariant, int raftersAmount)
    {
        raftersAmount = raftersAmount*(int)Math.floor(raftersVariant.getLength()/carportWidth);
        double rafterDistance = (float)carportLength / (raftersAmount + 2);
        rafterDistance = rafterDistance+raftersVariant.getHeight();
        Geometry3D rafters = createBox(0,0,0);
        for (int i = 0; i < raftersAmount; i++){
            Geometry3D raftersTemp = csg.translate3D(0,i*rafterDistance+(rafterDistance+ raftersVariant.getHeight()/2), 0).transform(createBox(carportWidth, raftersVariant.getHeight(), raftersVariant.getWidth()));
            rafters = csg.union3D(rafters, raftersTemp);
        }
        return csg.translate3DZ(300-raftersVariant.getWidth()/2).transform(rafters);
    }

    private static Geometry3D makeSternsSide(int carportWidth, int carportLength, ProductVariant sternSideVariant)
    {
        Geometry3D sternSideA = csg.translate3DX(carportWidth/2f).transform(createBox(sternSideVariant.getWidth(),carportLength,sternSideVariant.getHeight()));
        Geometry3D sternSideB = csg.translate3DX(-(carportWidth/2f)).transform(createBox(sternSideVariant.getWidth(),carportLength,sternSideVariant.getHeight()));
        return csg.translate3D(0,carportLength/2f, 300-sternSideVariant.getHeight()/2).transform(csg.union3D(sternSideA, sternSideB));
    }

    private static Geometry3D makeSternsFNB(int carportWidth, int carportLength, ProductVariant sternFNBVariant)
    {
        Geometry3D sternA = csg.translate3DY(carportLength).transform(createBox(carportWidth, sternFNBVariant.getWidth(), sternFNBVariant.getHeight()));
        Geometry3D sternB = csg.translate3DY(0).transform(createBox(carportWidth, sternFNBVariant.getWidth(), sternFNBVariant.getHeight()));
        return csg.translate3D(0,0,300-sternFNBVariant.getHeight()/2).transform(csg.union3D(sternA, sternB));
    }

    private static Geometry3D makeStraps(int carportWidth, int carportLength,float height, ProductVariant strapVariant)
    {
        Geometry3D StrapA = csg.translate3DX(carportWidth/2f-15 + (strapVariant.getHeight()/2)).transform(createBox(strapVariant.getHeight(), carportLength, strapVariant.getWidth()));
        Geometry3D StrapB = csg.translate3DX(-(carportWidth/2f)+15 - (strapVariant.getHeight()/2)).transform(createBox(strapVariant.getHeight(), carportLength, strapVariant.getWidth()));
        return csg.translate3D(0, carportLength/2f, 300-strapVariant.getWidth()/2-height).transform(csg.union3D(StrapA,StrapB));
    }

    private static Geometry3D makePosts(int carportWidth, int carportLength, float strapWidth, ProductVariant postVariant, int postAmount)
    {
        Geometry3D posts = createBox(0,0,0);
        Geometry3D postAA = csg.translate3D(carportWidth/2f-15-strapWidth/2,100 + postVariant.getWidth()/2, 0).transform(createBox(postVariant.getWidth(), postVariant.getHeight(), postVariant.getLength()));
        Geometry3D postBA = csg.translate3D(-(carportWidth/2f)+15+strapWidth/2,100 + postVariant.getWidth()/2, 0).transform(createBox(postVariant.getWidth(), postVariant.getHeight(), postVariant.getLength()));
        Geometry3D postBB = csg.translate3D(-(carportWidth/2f)+15+strapWidth/2,carportLength - 100 - postVariant.getWidth()/2, 0).transform(createBox(postVariant.getWidth(), postVariant.getHeight(), postVariant.getLength()));
        Geometry3D postAB = csg.translate3D(carportWidth/2f-15-strapWidth/2,carportLength - 100 - postVariant.getWidth()/2, 0).transform(createBox(postVariant.getWidth(), postVariant.getHeight(), postVariant.getLength()));
        float postDistance = (carportLength-200)/(float)((postAmount-4)/2+1);
        for (int i = 1; i < (postAmount-4)/2+1; i++) {
            Geometry3D tempPost0 = csg.translate3D(carportWidth/2f-15-strapWidth/2,postDistance*i + postVariant.getWidth()/2+100, 0).transform(createBox(postVariant.getWidth(), postVariant.getHeight(), postVariant.getLength()));
            Geometry3D tempPost1 = csg.translate3D(-(carportWidth/2f)+15+strapWidth/2,postDistance*i + postVariant.getWidth()/2+100, 0).transform(createBox(postVariant.getWidth(), postVariant.getHeight(), postVariant.getLength()));
            posts = csg.union3D(posts, tempPost0,tempPost1);
        }
        return csg.translate3DZ(postVariant.getLength()/2f).transform(csg.union3D(posts,postAA, postBA, postBB, postAB));
    }

    private static Geometry3D createBox(double width, double length, double height){
        //                  x      y       z
        return csg.box3D(width, length, height, true);
    }

    public static void main(String[] args)
    {
        csg = JavaCSGFactory.createDefault();
        ConnectionPool connectionPool = new ConnectionPool();
        try {
            Orders testOrder = OrderFacade.getOrderById(39, connectionPool);
            List<CompleteProduct> products = ItemListFacade.getCompletProduct(testOrder, connectionPool);
            Geometry3D carportTest = makeCarport(testOrder.getWidth(), testOrder.getLength(), products, connectionPool);
            csg.view(carportTest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
