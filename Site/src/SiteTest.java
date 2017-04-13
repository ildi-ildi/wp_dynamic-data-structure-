
public class SiteTest {
    
     public static void main(String[] args){
         String name = Input.getString("input home page's name: ");
         Site site = new Site(name);
         
         Integer option;
        do {
            System.out.println("0: quit");
            System.out.println("1: add page");
            System.out.println("2: display pages");
            option = Input.getInteger("input option: ");
            
            switch (option) {
                case 1:
                    name = Input.getString("input page's name: ");
                    try{
                    site.addSite(name);
                    }catch(Site.PageNotFoundException e){
                        System.out.println("insert invalid - value not unique");
                    }
                    break;
                case 2:
                    System.out.println(site);
                    break;
                case 0:
                    break;
            }
        }while (option != 0);
     }
}
