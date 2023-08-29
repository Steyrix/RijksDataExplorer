package com.example.rijksdataexplorer.core.data.response

fun getCollectionResponseJson() = "{\n" +
    "  \"elapsedMilliseconds\": 0,\n" +
    "  \"count\": 1,\n" +
    "  \"artObjects\": [\n" +
    "    {\n" +
    "      \"links\": {\n" +
    "        \"self\": \"http://www.rijksmuseum.nl/api/nl/collection/SK-C-5\",\n" +
    "        \"web\": \"http://www.rijksmuseum.nl/nl/collectie/SK-C-5\"\n" +
    "      },\n" +
    "      \"id\": \"nl-SK-C-5\",\n" +
    "      \"objectNumber\": \"SK-C-5\",\n" +
    "      \"title\": \"De Nachtwacht\",\n" +
    "      \"hasImage\": true,\n" +
    "      \"principalOrFirstMaker\": \"Rembrandt van Rijn\",\n" +
    "      \"longTitle\": \"De Nachtwacht, Rembrandt van Rijn, 1642\",\n" +
    "      \"showImage\": true,\n" +
    "      \"permitDownload\": true,\n" +
    "      \"webImage\": {\n" +
    "          \"guid\": \"aa08df9c-0af9-4195-b31b-f578fbe0a4c9\",\n" +
    "          \"offsetPercentageX\": 0,\n" +
    "          \"offsetPercentageY\": 1,\n" +
    "          \"width\": 2500,\n" +
    "          \"height\": 2034,\n" +
    "          \"url\":\"https://lh3.googleusercontent.com/J-mxAE7CPu-DXIOx4QKBtb0GC4ud37da1QK7CzbTIDswmvZHXhLm4Tv2-1H3iBXJWAW_bHm7dMl3j5wv_XiWAg55VOM=s0\"\n" +
    "      },\n" +
    "      \"headerImage\": {\n" +
    "        \"guid\": \"29a2a516-f1d2-4713-9cbd-7a4458026057\",\n" +
    "        \"offsetPercentageX\": 0,\n" +
    "        \"offsetPercentageY\": 0,\n" +
    "        \"width\": 1920,\n" +
    "        \"height\": 460,\n" +
    "        \"url\": \"https://lh3.googleusercontent.com/O7ES8hCeygPDvHSob5Yl4bPIRGA58EoCM-ouQYN6CYBw5jlELVqk2tLkHF5C45JJj-5QBqF6cA6zUfS66PUhQamHAw=s0\"\n" +
    "      },\n" +
    "      \"productionPlaces\": [\"Amsterdam\"]\n" +
    "    }\n" +
    "  ]\n" +
    "}"

fun getCollectionResponse() = CollectionResponse(
    artObjects = listOf(
        ArtObjectDto(
            id = "nl-SK-C-5",
            objectNumber = "SK-C-5",
            title = "De Nachtwacht",
            longTitle = "De Nachtwacht, Rembrandt van Rijn, 1642",
            principalOrFirstMaker = "Rembrandt van Rijn",
            showImage = true,
            headerImage = HeaderImageDto(
                url = "https://lh3.googleusercontent.com/O7ES8hCeygPDvHSob5Yl4bPIRGA58EoCM-ouQYN6CYBw5jlELVqk2tLkHF5C45JJj-5QBqF6cA6zUfS66PUhQamHAw=s0"
            )
        )
    )
)

fun getCollectionDetailsResponseJson() = "{\n" +
    "  \"elapsedMilliseconds\": 219,\n" +
    "  \"artObject\": {  \n" +
    "    \"links\": {  \n" +
    "      \"search\":\"http://www.rijksmuseum.nl/api/nl/collection\"\n" +
    "    },\n" +
    "    \"id\": \"nl-SK-C-5\",\n" +
    "    \"priref\": \"5216\",\n" +
    "    \"objectNumber\": \"SK-C-5\",\n" +
    "    \"language\": \"nl\",\n" +
    "    \"title\": \"De Nachtwacht\",\n" +
    "    \"copyrightHolder\": null,\n" +
    "    \"webImage\":{  \n" +
    "      \"guid\": \"aa08df9c-0af9-4195-b31b-f578fbe0a4c9\",\n" +
    "      \"offsetPercentageX\": 50,\n" +
    "      \"offsetPercentageY\": 100,\n" +
    "      \"width\": 2500,\n" +
    "      \"height\": 2034,\n" +
    "     \"url\": \"https://lh3.googleusercontent.com/J-mxAE7CPu-DXIOx4QKBtb0GC4ud37da1QK7CzbTIDswmvZHXhLm4Tv2-1H3iBXJWAW_bHm7dMl3j5wv_XiWAg55VOM=s0\"\n" +
    "    }," +
    "    \"titles\": [  \n" +
    "       \"Officieren en andere schutters van wijk II in Amsterdam, onder leiding van kapitein Frans Banninck Cocq en luitenant Willem van Ruytenburch, bekend als ‘De Nachtwacht’\",\n" +
    "         \"Het korporaalschap van kapitein Frans Banninck Cocq en luitenant Willem van Ruytenburch, bekend als de 'Nachtwacht'\"\n" +
    "    ],\n" +
    "    \"description\": \"Officieren en andere schutters van wijk II in Amsterdam onder leiding van kapitein Frans Banninck Cocq en luitenant Willem van Ruytenburch, sinds het einde van de 18de eeuw bekend als ‘De Nachtwacht’. Schutters van de Kloveniersdoelen uit een poort naar buiten tredend. Op een schild aangebracht naast de poort staan de namen van de afgebeelde personen: Frans Banning Cocq, heer van purmerlant en Ilpendam, Capiteijn Willem van Ruijtenburch van Vlaerdingen, heer van Vlaerdingen, Lu[ij]tenant, Jan Visscher Cornelisen Vaendrich, Rombout Kemp Sergeant, Reijnier Engelen Sergeant, Barent Harmansen, Jan Adriaensen Keyser, Elbert Willemsen, Jan Clasen Leydeckers, Jan Ockersen, Jan Pietersen bronchorst, Harman Iacobsen wormskerck, Jacob Dircksen de Roy, Jan vander heede, Walich Schellingwou, Jan brugman, Claes van Cruysbergen, Paulus Schoonhoven. De schutters zijn gewapend met onder anderen pieken, musketten en hellebaarden. Rechts de tamboer met een grote trommel. Tussen de soldaten links staat een meisje met een dode kip om haar middel, rechts een blaffende hond. Linksboven de vaandrig met de uitgestoken vaandel.\",\n" +
    "    \"principalMakers\": [  \n" +
    "      {  \n" +
    "        \"name\": \"Rembrandt van Rijn\",\n" +
    "        \"unFixedName\": \"Rijn, Rembrandt van\",\n" +
    "        \"placeOfBirth\": \"Leiden\",\n" +
    "        \"dateOfBirth\": \"1606-07-15\",\n" +
    "        \"dateOfBirthPrecision\": null,\n" +
    "        \"dateOfDeath\": \"1669-10-08\",\n" +
    "        \"dateOfDeathPrecision\": null,\n" +
    "        \"placeOfDeath\": \"Amsterdam\",\n" +
    "        \"occupation\": [  \n" +
    "          \"prentmaker\",\n" +
    "          \"tekenaar\",\n" +
    "          \"schilder\"\n" +
    "        ],\n" +
    "        \"roles\":[  \n" +
    "          \"schilder\"\n" +
    "        ],\n" +
    "        \"nationality\": \"Noord-Nederlands\",\n" +
    "        \"biography\": null,\n" +
    "        \"productionPlaces\": [  \n" +
    "          \"Amsterdam\"\n" +
    "        ],\n" +
    "        \"qualification\": null\n" +
    "      }\n" +
    "    ],\n" +
    "    \"plaqueDescriptionDutch\": \"Rembrandts beroemdste en grootste doek werd gemaakt voor de Kloveniersdoelen. Dit was een van de verenigingsgebouwen van de Amsterdamse schutterij, de burgerwacht van de stad. \\r\\nRembrandt was de eerste die op een groepsportret de figuren in actie weergaf. De kapitein, in het zwart, geeft zijn luitenant opdracht dat de compagnie moet gaan marcheren. De schutters stellen zich op. Met behulp van licht vestigde Rembrandt de aandacht op belangrijke details, zoals het handgebaar van de kapitein en het kleine meisje op de achtergrond. Zij is de mascotte van de schutters.\",\n" +
    "    \"plaqueDescriptionEnglish\": \"Rembrandt’s largest, most famous canvas was made for the Arquebusiers guild hall. This was one of several halls of Amsterdam’s civic guard, the city’s militia and police. \\r\\nRembrandt was the first to paint figures in a group portrait actually doing something. The captain, dressed in black, is telling his lieutenant to start the company marching. The guardsmen are getting into formation. Rembrandt used the light to focus on particular details, like the captain’s gesturing hand and the young girl in the foreground. She was the company mascot.\\r\\n\",\n" +
    "    \"principalMaker\": \"Rembrandt van Rijn\"" +
    "}" +
    "}"

fun getCollectionDetailsResponse() = CollectionDetailsResponse(
    artObject = ObjectDetailsDto(
        id = "nl-SK-C-5",
        objectNumber = "SK-C-5",
        webImage = WebImageDto(
            url = "https://lh3.googleusercontent.com/J-mxAE7CPu-DXIOx4QKBtb0GC4ud37da1QK7CzbTIDswmvZHXhLm4Tv2-1H3iBXJWAW_bHm7dMl3j5wv_XiWAg55VOM=s0"
        ),
        titles = listOf(
            "Officieren en andere schutters van wijk II in Amsterdam, onder leiding van kapitein Frans Banninck Cocq en luitenant Willem van Ruytenburch, bekend als ‘De Nachtwacht’",
            "Het korporaalschap van kapitein Frans Banninck Cocq en luitenant Willem van Ruytenburch, bekend als de 'Nachtwacht'"
        ),
        description = "Officieren en andere schutters van wijk II in Amsterdam onder leiding van kapitein Frans Banninck Cocq en luitenant Willem van Ruytenburch, sinds het einde van de 18de eeuw bekend als ‘De Nachtwacht’. Schutters van de Kloveniersdoelen uit een poort naar buiten tredend. Op een schild aangebracht naast de poort staan de namen van de afgebeelde personen: Frans Banning Cocq, heer van purmerlant en Ilpendam, Capiteijn Willem van Ruijtenburch van Vlaerdingen, heer van Vlaerdingen, Lu[ij]tenant, Jan Visscher Cornelisen Vaendrich, Rombout Kemp Sergeant, Reijnier Engelen Sergeant, Barent Harmansen, Jan Adriaensen Keyser, Elbert Willemsen, Jan Clasen Leydeckers, Jan Ockersen, Jan Pietersen bronchorst, Harman Iacobsen wormskerck, Jacob Dircksen de Roy, Jan vander heede, Walich Schellingwou, Jan brugman, Claes van Cruysbergen, Paulus Schoonhoven. De schutters zijn gewapend met onder anderen pieken, musketten en hellebaarden. Rechts de tamboer met een grote trommel. Tussen de soldaten links staat een meisje met een dode kip om haar middel, rechts een blaffende hond. Linksboven de vaandrig met de uitgestoken vaandel.",
        principalMaker = "Rembrandt van Rijn"
    )
)