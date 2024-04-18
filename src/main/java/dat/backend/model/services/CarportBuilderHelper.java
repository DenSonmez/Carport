package dat.backend.model.services;


import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.CompleteProduct;
import dat.backend.model.entities.ItemEntry;
import dat.backend.model.entities.Orders;
import dat.backend.model.entities.ProductVariant;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.ItemListFacade;
import dat.backend.model.persistence.ItemListMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Det CarportBuilderHelper klasse er med til beregne vores stykliste
 */

public class CarportBuilderHelper {

    private static float price = 0;

    /**
     * Genererer en liste over CompleteProduct-objekter baseret på dimensioner og ordreoplysninger.
     *
     * @param width  Bredde på produktet.
     * @param length Længde på produktet.
     * @param order  Ordreobjektet, der indeholder relevante oplysninger.
     * @return En liste over CompleteProduct-objekter.
     * @throws DatabaseException Hvis der opstår en databasefejl under processen.
     */

    public static List<CompleteProduct> generateItemList(int width, int length, Orders order) throws DatabaseException {
        price = 0;
        ConnectionPool connectionPool = ApplicationStart.getConnectionPool();
        List<CompleteProduct> completeProducts = ItemListFacade.getCompletProduct(order, connectionPool);
        if(completeProducts.size() == 0) {

            List<ItemEntry> itemEntryList = new ArrayList<>();
            List<ItemEntry> posts = getPostAmount(width, length, order.getId());
            List<ItemEntry> straps = getStrapAmount(length, order.getId());
            itemEntryList.addAll(getSternFrontAndBackAmount(width, order.getId()));
            itemEntryList.addAll(getSideSternAmount(length, order.getId()));
            List<ItemEntry> rafters = getRaftersAmount(width, length, order.getId());
            itemEntryList.addAll(getRoofAmount(width, length, order.getId()));
            itemEntryList.addAll(getScrewsAmount(order.getId()));
            itemEntryList.addAll(getBracketAmount(rafters.size(), order.getId()));
            itemEntryList.addAll(getBracketScrewsAmount(rafters.size(), order.getId()));
            itemEntryList.addAll(getHollowtiesAmount(width, length, order.getId()));
            itemEntryList.addAll(getPostBoltAmount(posts.size(), straps.size(), order.getId()));
            itemEntryList.addAll(posts);
            itemEntryList.addAll(straps);
            itemEntryList.addAll(rafters);
            order.setDbPrice(price * 1.20f, connectionPool);

            for (ItemEntry items : itemEntryList) {
                ItemListFacade.addItemList(items, connectionPool);
            }
            completeProducts = ItemListFacade.getCompletProduct(order, connectionPool);
        }
        return completeProducts;
    }
/**
 * Beregner antallet af postelementer (stolper) og opretter en liste over dem baseret på produktets dimensioner (bredde og længde) og ordreoplysninger.
 * Hvis både bredden og længden er over 5 meter og 4,80 meter, anvendes der 6 stolper. Ellers anvendes der 4 stolper.
 * Metoden opretter en liste af ItemEntry-objekter, der repræsenterer postelementerne i ordren.
 *  @param width Bredde på på stolpen
 * @param length Længde på stolpen
 * @param orderId er id´en den tilhørende ordre
 * @return En liste over ItemEntry-objekter, der repræsenterer postelementerne.
 * */
    //Stolpe: Alt over 5 x 4,80 m skal der bruges 6 stolper, alt under er det skal der bruges 4 stk.
    public static List<ItemEntry> getPostAmount(int width, int length, int orderId) {
        float pricePrUnit = 0.58f;
        int amount = 4;
        int widthTmp = width;
        int lengthTmp = length;

        while (widthTmp > 500 && lengthTmp > 480) {
            amount += 2;
            widthTmp -= 500;
            lengthTmp -= 480;
        }
        // This is the post in the database that we want to use, h:9.7 cm, w:9.7 cm
        int postProductVariantId = 7;
        List<ItemEntry> itemEntryList = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            itemEntryList.add(new ItemEntry(orderId, postProductVariantId));
            price += pricePrUnit * 300;
        }
        return itemEntryList;
    }

    /**
    *  Beregner antallet af remme og opretter en liste over dem baseret på længden og ordre-id'et.
     * Hvis længden er over 6 meter, anvendes der 4 remme, ellers anvendes der 2 remme.
     * Metoden opretter en liste af ItemEntry-objekter, der repræsenterer remmene i ordren.
     * @param length Længde af remmen i cm
     * @param orderId id´et for den pågældende ordre
     * @return Den returende en liste over itemEntry objekter
     */

    //Rem: Alt over 6 m længde skal der bruges 4 rem, alt under er det skal der bruges 2 stk.
    public static List<ItemEntry> getStrapAmount(int length, int orderId) {
        float pricePrUnit = 0.37f;

        int amount = 2;
        int lengthTmp = length;

        while (lengthTmp > 600) {
            amount += 2;
            lengthTmp -= 600;
        }

        int strapProductVariantId = 9;
        List<ItemEntry> itemEntryList = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            itemEntryList.add(new ItemEntry(orderId, strapProductVariantId));
            price += pricePrUnit * 600;
        }

        return itemEntryList;
    }

    /**
     * Beregner for og bag Stern og oprette en liste baseret på bredden og orderId
     * Hver for- og bag stern en standardbredde på 360 cm
     * Metoden justerer antallet af for- og bag stern baseret på den angivne bredde og opretter en liste af ItemEntry-objekter,
     * der repræsenterer for- og bag stern i ordren.
     * sternProductVariantId = 1 repræsenterer det id der er tilknyttet til stern i databaser
     * @param width bredden for både for og bagved stern i cm
     * @param orderId er id´et for den tilhørende ordre id
     * @return Den returner en liste over for/bag stern som itemEntry objekter
     *
     * */

    //Stern; foran og bagved. Alt under 360 cm skal der bruges 4 (2 stk foran 2 stk bagved), alt over er det skal der plusses med 1.
    public static List<ItemEntry> getSternFrontAndBackAmount(int width, int orderId) {
        float pricePrUnit = 0.37f;
        int amount = 2;
        int widthTmp = width * 2 - amount * 360;

        while (widthTmp > 360) {
            amount += 1;
            widthTmp -= 360;
        }

        int sternProductVariantId = 1;
        List<ItemEntry> itemEntryList = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            itemEntryList.add(new ItemEntry(orderId, sternProductVariantId));
            price += pricePrUnit * 360;
        }

        return itemEntryList;
    }

/**
 * * Beregner antallet af side stern og opretter en liste over dem udefra på længden og ordre-id'et.
 * Metoden justerer antallet af sidestammer baseret på længden ved at tage højde for, hvor mange hele sidestammer der kan skæres ud af længden.
 * @param length Længde af side stern i cm (Standardlængden af hver sidestamme er 540 centimeter.)
 * @param orderId er id´et for den tilhørende ordre id (en forbindelse mellem de genererede ItemEntry-objekter og den specifikke ordre, hvorpå sidestammerne skal tilføjes)
 * @return en liste over side stern som itemEntry objekter
 * */

    //Siderne sættes sammen (540cm), og antallet bregnes udefra dette. Vi ganger det med 2 og divider med max længde stern.
    public static List<ItemEntry> getSideSternAmount(int length, int orderId) {
        float pricePrUnit = 0.37f;
        int amount = 2;
        int lengthTmp = length * 2 - amount * 540;

        while (lengthTmp > 540) {
            amount += 1;
            lengthTmp -= 540;
        }

        int sternProductVariantId = 5;
        List<ItemEntry> itemEntryList = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            itemEntryList.add(new ItemEntry(orderId, sternProductVariantId));
            price += pricePrUnit * 540;
        }

        return itemEntryList;
    }

    /**
     * Beregner antallet af spær og opretter en liste over dem baseret på dimensionerne (bredde og længde) og ordreoplysninger.
     * Metoden beregner antallet af spær ved at opdele længden i passende intervaller baseret på den givne bredde.
     * Metoden opretter en liste af ItemEntry-objekter, der repræsenterer spærene i ordren.
     * @param width    Bredde på spæret i cm
     * @param length   Længde på spæret i cm
     * @param orderId  Id'et for den tilhørende ordre.
     * @return En liste over spær som ItemEntry-objekter.
     */

    //Spær: 60 x 2 – længde af carporten og dividere med 0,59 så får vi det antal stk vi skal bruge.
// Bredde <= 300 kan vi få to ud af det.
    public static List<ItemEntry> getRaftersAmount(int width, int length, int orderId) {
        float pricePrUnit = 0.28f;
        int amount = 0;
        int lengthTmp = length - 2 * 60;
        int raftersLength = 0;

        int amountTmp = (int) (600f / width);

        while (raftersLength < lengthTmp) {
            raftersLength += amountTmp * 60;
            amount++;
        }

        int raftersProductVariantId = 10;
        List<ItemEntry> itemEntryList = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            itemEntryList.add(new ItemEntry(orderId, raftersProductVariantId));
            price += pricePrUnit * 600;
        }

        return itemEntryList;
    }

    public static float getRafterDistance(int length, int amount) {

        return (float) Math.round((double) (length / (amount + 2)) * 10) / 10;

    }
    /**
     * Beregner antallet af tagplader og tilbehør og opretter en liste over dem baseret på dimensionerne (bredde og længde) og ordreoplysninger.
     * Metoden tager højde for dimensionerne og beregner antallet af tagplader og skruer baseret på disse dimensioner.
     * Metoden opretter en liste af ItemEntry-objekter, der repræsenterer tagplader og tilbehør i ordren.
     * @param width    Bredde på taget i cm
     * @param length   Længde på taget i cm
     * @param orderId  Id'et for den tilhørende ordre.
     * @return En liste over tagplader og tilbehør som ItemEntry-objekter.
     */
//tag
    public static List<ItemEntry> getRoofAmount(int width, int length, int orderId) {

        float pricePrUnit = 0.57f;
        int pricePrUnitScrew = 429;

        int amount = 0;
        int widthAmount = (int) Math.ceil(width / 109f);

        int smallVarAmount = (int) Math.ceil(length / 240f);
        int largeVarAmount = (int) Math.ceil(length / 600f);

        int smallVarWaste = smallVarAmount * 240 - length;
        int largeVarWaste = largeVarAmount * 600 - length;
        int roofScrewAmount = 0;

        List<ItemEntry> itemEntryList = new ArrayList<>();
        if (smallVarWaste < largeVarWaste) {
            int roofProductVariantId = 15;
            for (int i = 0; i < smallVarAmount * widthAmount; i++) {
                itemEntryList.add(new ItemEntry(orderId, roofProductVariantId));
                price += pricePrUnit * 240;
                roofScrewAmount++;
            }

        } else {
            int roofProductVariantId = 16;
            if (largeVarWaste < 0) {
                itemEntryList.add(new ItemEntry(orderId, roofProductVariantId));
                roofScrewAmount += 2;
                int extraOfSmallVar = (int) Math.max(Math.ceil((length - 600f) / 240f), 0);
                price += pricePrUnit * 600;
                for (int i = 0; i < extraOfSmallVar; i++) {
                    itemEntryList.add(new ItemEntry(orderId, 15));
                    price += pricePrUnit * 240;
                    roofScrewAmount++;
                }
            } else {

                for (int i = 0; i < largeVarAmount * widthAmount; i++) {
                    itemEntryList.add(new ItemEntry(orderId, roofProductVariantId));
                    price += pricePrUnit * 600;
                    roofScrewAmount += 2;

                }
            }
        }
        roofScrewAmount = (int) Math.max(Math.floor(roofScrewAmount / 4f),1);
        for (int i = 0; i < roofScrewAmount; i++) {
            itemEntryList.add(new ItemEntry(orderId, 17));
            price += pricePrUnitScrew;

        }
        return itemEntryList;
    }

    //bræddebolt og firkantskiver
    public static List<ItemEntry> getPostBoltAmount(int postAmount, int strapAmount, int orderId) {
        int pricePrUnitBolt = 15;
        float pricePrUnitSquareWashers = 8.8f;


        int amount = postAmount * strapAmount;

        List<ItemEntry> itemEntryList = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            itemEntryList.add(new ItemEntry(orderId, 14));
            itemEntryList.add(new ItemEntry(orderId, 19));
            price += pricePrUnitBolt + pricePrUnitSquareWashers;
        }
        return itemEntryList;
    }

    //beslag til højre og venstre
    public static List<ItemEntry> getBracketAmount(int raftersAmount, int orderId) {
        int pricePrUnit =  49;
        List<ItemEntry> itemEntryList = new ArrayList<>();
        for (int i = 0; i < raftersAmount; i++) {
            itemEntryList.add(new ItemEntry(orderId, 11));
            itemEntryList.add(new ItemEntry(orderId, 18));
            price += pricePrUnit * 2;
        }
        return itemEntryList;
    }

    //skruer
    public static List<ItemEntry> getScrewsAmount(int orderId) {
        float pricePrUnit = 259;
        List<ItemEntry> itemEntryList = new ArrayList<>();
        itemEntryList.add(new ItemEntry(orderId, 13));
        price += pricePrUnit;
        return itemEntryList;
    }


    //hulbånd
    public static List<ItemEntry> getHollowtiesAmount(int width, int length, int orderId) {
        int pricePrUnit = 349;
        int amount = 1;
        int triangleWidth = (width - 30);
        int triangleLength = length;
        float hypotenuse = (float) Math.sqrt(Math.pow(triangleWidth, 2) + Math.pow(triangleLength, 2));
        amount = (int) Math.ceil(hypotenuse * 2 / 1000);


        List<ItemEntry> itemEntryList = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            itemEntryList.add(new ItemEntry(orderId, 20));
            price += pricePrUnit;
        }
        return itemEntryList;
    }


    //beslagskruer
    public static List<ItemEntry> getBracketScrewsAmount(int raftersAmount, int orderId) {
        int pricePrUnit = 349;
        int amount = raftersAmount / 5;

        List<ItemEntry> itemEntryList = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            itemEntryList.add(new ItemEntry(orderId, 21));
            price += pricePrUnit;
        }
        return itemEntryList;
    }


    public static float startPrice(int width, int length) {
        /*  - Vi skal beregne en pris ud fra længde x bredde, som bliver en "start-pris"
            - .... før kunden rammer salgsteamet for en endelig pris */
        int CustomerChosenArea = width * length;
        float price = (float) (CustomerChosenArea * 0.08);

        return price;
    }

}