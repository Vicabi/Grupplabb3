# Teststrategi: Todolista
## Mål

Att garantera hög kvalitet på vår service och minimera risken för buggar och problem i produktionsmiljö.

## Strategiöversikt

* **Enhetstester:** dessa testar systemets minsta funktioner och metoder, vanligtvis genom att forcera ett event eller en statusförändring.. Dessa testar i varje fall endast en komponent och inkluderar inte externa tjänster som exempelvis databaser. Dessa ska köras mycket snabbt(millisekunder) och ge hög kodtäckning.
  
* **Integrationstester:** vi ska testa hur våra komponenter och tjänster samverkar och fungerar tillsammans med andra tjänster. Testerna ska hantera enkla fall, verifieringsfel och prestanda.
  
* **Smoke tests:** denna testfunktion är till för att snabbt säkerställa grundläggande funktioner i vår applikation. Testerna ska kontrollera om den senaste deployade koden fortfarande innebär att de mest kritiska funktionerna i applikationen fungerar.
  
## Detaljerad strategi

### Enhetstester
* **Verktyg:** JUnit, Mockito.
* **Frekvens:** Ska köras vid varje bygge.
* **Mål:** Minst 80% kodtäckning.

### Integrationstester
* **Verktyg:** Spring Test med MockMvc
* **Frekvens:** Ska köras vid varje bygge.
* **Mål:** säkerställa att kritiska komponenter och tjänster interagerar korrekt och pålitligt.
  
### Smoke tests
* **Verktyg:** RestAssured, Postman eller liknande verktyg för CRUD-anrop.
* **Frekvens:** Ska köras efter varje deployment till en miljö.
* **Mål:** att snabbt verifiera att kritiska komponenter fungerar korrekt efter varje kodändring.
  
## CI/CD Pipeline
* **Automatisk Distribution:** Möjliggör en automatisk distribution av kodändringar till testmiljöer efter framgångsrika enhets- och integrationstester. Detta skapar effektivitet genom att snabbt kunna testa ny kod i en verklig miljö.
* **Fail Fast:** Implementera en omedelbar avstängningsmekanism i det fall att något av de tidigare beskrivna teststegen misslyckas. Detta garanterar snabb identifiering och hantering av eventuella problem.
* **Manuellt Godkännande:** Kräv manuellt godkännande innan kodändringar distribueras till produktionsmiljön. Denna process ger teamet ytterligare kontroll och säkerställer att endast noggrant testad kod släpps i produktion.
