package dat.backend;

/**
 * Det her er hoved-pakken for vores carport-projekt, der giver kunder mulighed for at bestille en carport baseret på længde og bredde, efter de har registreret sig og logget ind.
 * Systemet har også en administrationsfunktion, hvor administratorer kan tjekke kunde oplysninger, kontrollere ordrer, og håndtage produkter.
 *
 * Pakkerne i vores carport indeholder:
 *
 *
 * - control-package: Som holder på vores servlet-klasser, der håndterer webanmodninger og styrer kommunikationen mellem frontend og backend.
 * @see dat.backend.control
 *
 *
 * - model-package: Indeholder underpakkerne config, entities, exceptions, persistence og services.
 * @see dat.backend.model
 *
 *
 * - config-package: har en klasse, der hedder AplicationStart-klassen som styrer oprettelsen og adgangen til connectionPool, som er afgørende for at håndtere databaseforbindelser i webapplikationen.
 * @see dat.backend.model.config
 *
 *
 * - entities-package: Indeholder klasser, der repræsenterer entiteter i systemet, såsom brugere, ordrer og produkter osv. Disse klasser indeholder attributter, konstruktører og metoder til at håndtere data.
 * @see dat.backend.model.entities
 *
 *
 * - exceptions-package: Indeholder exception-klasser, der fanger og håndterer fejl i systemet ved databasefejl
 * @see dat.backend.model.exceptions
 *
 *
 * - persistence-package: Indeholder mapperklasser, der håndterer kommunikationen med databasen og udfører CRUD-operationer med entiteterne. Disse klasser bruger JDBC til at interagere med databasen
 *  @see dat.backend.model.persistence
 *
 *
 * - services-package: Indeholder hjælpeklasser og services, der udfører forskellige opgaver i systemet.
 *  Dette holder en CarportBuilderHelper-klasse, der beregner stykliste baseret på en carportdimensioner,
 *  en DownloadHelper-klasse, der hjælper med at konvertere data til CSV-filer, en ThreeDBuilder som tager en stykeliste laver den om til 3D print,
 *  og en RegisterHelper-klasse, der hjælper med at generere og validere hashede adgangskoder for at håndtere sikkerhed ved adgangskoder.
 * @see dat.backend.model.services
 *
 *
 *
 */
