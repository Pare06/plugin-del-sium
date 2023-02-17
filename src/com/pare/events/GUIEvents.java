package com.pare.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUIEvents implements Listener {
    // prima guarda GUICommand

    // questo metodo parte ogni volta che qualcuno clicca in un inventario
    @EventHandler
    public void onPlayerInventory(InventoryClickEvent event) {
        // l'inventario cliccato
        InventoryView inventory = event.getView();
        // è l'inventario del giocatore?
        if (inventory.getType() == InventoryType.PLAYER) {
            return;
        }

        Player player = (Player) event.getWhoClicked();
        ItemStack itemClicked = event.getCurrentItem();
        // cliccato fuori dall'inventario?
        if (itemClicked == null) {
            return;
        }
        ItemMeta itemMeta = itemClicked.getItemMeta();
        // obbligatorio altrimenti java piange
        if (itemMeta == null) {
            return;
        }

        // è l'interfaccia delle copypasta?
        if (inventory.getTitle().equals("COPYPASTA SELEZIONATORE")) {
            // in java esiste uno switch super sospetto che permette di inizializzare una variabile con uno switch
            // sintassi: (classe) (nome_variabile) = switch (espressione) {
            //                  case (valore) -> (valore_da_assegnare);             <- questo tipo di case si può usare
            //                  ...                                                    anche negli switch normali
            //                  default -> (valore_default);
            //            };
            String message = switch (itemMeta.getDisplayName()) {
                case "avigliano" -> AVIGLIANO;
                case "puglia" -> PUGLIA;
                case "simone" -> SIMONE;
                case "bona dea" -> BONADEA;
                // vetro?
                default -> NON_SONO_DISABILE;
            };

            player.sendMessage(message);
        }
    }

    // final è l'equivalente di const del c++
    private static final String AVIGLIANO = "È arrivata mammina la piscia r ta mamma, scrivi scrivi. Si sol n'abbunat.";

    // per le stringhe stralunghe / con tanti \n (endl in c++) esistono le stringhe con 3 apici ("""sium""")
    // per andare a capo in queste stringhe basta andare alla riga sotto
    private static final String PUGLIA = """
            Egregio Sig. Cagnaccio Puglia Dario,
            
            sono il Vostro più grande ammiratore.
            Vorrei umilmente chiederLe se potrebbe spiegarmi la lore
            delle onorevoli sette coltellate tirate a un suo coetaneo,
            fuori dalla Vostra scuola, IPSAR G. Matteotti, anche detto "Onorevole Istituto Alberghiero".
            La prego umilmente di spiegarmi il motivo delle coltellate,
            e soprattutto il bersaglio così che io possa finire ciò che Lei ha cominciato
            e accoltellare la sua famiglia.
            In caso non mi Volesse dire nè il motivo nè a chi le ha tirate,
            La prego gentilmente di non pusharmi sotto casa
            e soprattutto di non farmi Pompa Tattico Headshot quando mi vede bere vodka con Mihai Marocci.

            Cordiali Saluti,
            Bismillah "Puccia" Kebe
            """;

    private static final String SIMONE = """
            Simone sei un coglione
            Ti rompo in due, sei stupido come un bidone
            Ti ammazzo, di te non me ne frega un cazzo
            Ti spacco di botte per te sarà la buonanotte
            Brutto stronzo, non fare il ganzo, ti mangio a colazione
            Sembri spazzatura, sei un bidone (ah)
            Sei un bidone, sei un coglione
            Sei (risate da mongolo) un bidone
            Sei un coglione, ti rompo in due, sei stupido come un bue
            Ti spacco di botte, per te sarà la buonanotte
            Ritornatene ad Auschwitz brutto pagliaccio
            """;

    private static final String BONADEA = """
            Ristorante veramente squisito e delizioso,
            peccato però per il figlio della proprietaria che sembra uscito dal ghetto del Burkina Faso
            e il marito della proprietaria che si è appena fatto qualche torbone
            e mi ha ficcato una sigaretta su per il culo.
            Apparte quelle piccole inconvenienze il cibo assomigliava
            a una busta di plastica di quelle usate per fare gli scherzoni ai delfini bastardi
            che inquinano i nostri mari.
            L'acqua sembrava presa direttamente dall'Arno, proprio nei punti dove ci sta il petrolio.
            Non tornerò mai più a questo terribile posto perchè sarò occupato a chiamare lda polizia
            perchè quel macaco di Mihai Marocci chiama la gheng di Ibrahim Moutawakil e Matteo Ragoni.
            """;

    private static final String NON_SONO_DISABILE = "sei disabile dio boia hai cliccato sul vetro";
}
