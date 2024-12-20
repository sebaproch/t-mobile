Feature: Wybranie telefonu z listy ofert

  Scenario: Wybor telefonu i dodanie do koszyka
    Given Otwieram przegladarke na stronie "https://www.t-mobile.pl/"
    When Wybieram smartfony bez abonamentu
    And Klikam w pierwszy element listy
    And Dodaje produkt do koszyka
    Then Przechodze na strone glowna T-Mobile
