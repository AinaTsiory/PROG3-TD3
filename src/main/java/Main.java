/*public class Main {
    public static void main(String[] args) {
        DataRetriever dataRetriever = new DataRetriever();
        Dish saladeVerte = dataRetriever.findDishById(1);
        System.out.println(saladeVerte);

        Dish poulet = dataRetriever.findDishById(2);
        System.out.println(poulet);

        Dish rizLegume = dataRetriever.findDishById(3);
        rizLegume.setPrice(100.0);
        Dish newRizLegume = dataRetriever.saveDish(rizLegume);
        System.out.println(newRizLegume); // Should not throw exception


//        Dish rizLegumeAgain = dataRetriever.findDishById(3);
//        rizLegumeAgain.setPrice(null);
//        Dish savedNewRizLegume = dataRetriever.saveDish(rizLegume);
//        System.out.println(savedNewRizLegume); // Should throw exception

        Ingredient laitue = dataRetriever.findIngredientById(1);
        System.out.println(laitue);

    }

}
*/

import java.time.Instant;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        DataRetriever repo = new DataRetriever();

        System.out.println("\nÉtape 1 : Création d'une commande à emporter");
        Order commande = new Order();
        commande.setOrderType(OrderTypeEnum.TAKE_AWAY);

        DishOrder ligne = new DishOrder();
        try {
            Dish plat = repo.findDishById(1); // Change 1 si ton plat a un autre ID
            ligne.setDish(plat);
            ligne.setQuantity(2); // 2 portions
        } catch (Exception e) {
            System.err.println("Échec préparation commande (plat non trouvé ?) : " + e.getMessage());
            return;
        }

        commande.setDishOrders(new ArrayList<>());
        commande.getDishOrders().add(ligne);

        System.out.println("Commande créée :");
        System.out.println("  - Type : " + commande.getOrderType());
        System.out.println("  - Nombre de plats : " + commande.getDishOrders().size());
        System.out.println("  - Total HT estimé : " + commande.getTotalAmountWithoutVAT());
}


        System.out.println("\nÉtape 2 : Sauvegarde de la commande");
    Order savedOrder = null;
        try {
        savedOrder = repo.saveOrder(commande);
        System.out.println("\n=== SAUVEGARDE RÉUSSIE ===");
        System.out.println("Référence générée : " + savedOrder.getReference());
        System.out.println("ID de la commande : " + savedOrder.getId());
        System.out.println("Type : " + savedOrder.getOrderType());
        System.out.println("Statut : " + savedOrder.getStatus());
        System.out.println("Date de création : " + savedOrder.getCreationDatetime());
        System.out.println("Nombre de lignes : " + savedOrder.getDishOrders().size());
        System.out.println("Total HT : " + savedOrder.getTotalAmountWithoutVAT());
        System.out.println("Total TTC : " + savedOrder.getTotalAmountWithVAT());
    } catch (RuntimeException e) {
        System.out.println("\n=== ÉCHEC SAUVEGARDE (souvent stock insuffisant) ===");
        System.out.println("Message : " + e.getMessage());
        // Pas de return ici → on continue pour tester les étapes suivantes si possible
    } catch (Exception e) {
        System.err.println("Erreur inattendue lors de saveOrder : " + e.getMessage());
        e.printStackTrace();
    }

}

