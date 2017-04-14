
public class SiteTest {

    public static void main(String[] args) {
        String name = Input.getString("input home page's name: ");
        Site site = new Site(name);

        Integer option;
        do {
            System.out.println("0: quit");
            System.out.println("1: add page");
            System.out.println("2: move down");
            System.out.println("3: move up");
            System.out.println("4: display current page content");
            System.out.println("5: display site map");
            option = Input.getInteger("input option: ");

            switch (option) {
                case 1:
                    name = Input.getString("input page's name: ");
                    try {
                        site.addSite(name);
                    } catch (Site.PageNotFoundException e) {

    
     public static void main(String[] args){
         String name = Input.getString("input home page's name: ");
         Site site = new Site(name);
         
         Integer option;
        do {
            System.out.println("0: quit");
            System.out.println("1: add page");
            System.out.println("2: move down");
            System.out.println("3: move up");
            System.out.println("4: display current page content");
            System.out.println("5: display site map");

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


                    System.out.println(site.getCurrent());
                    try {
                        if (site.checkIfHasCurrentDown() == true) {
                            String namePage = Input.getString("page name: ");
                            site.moveDown();
                            site.findSite(namePage);
                        }
                    } catch (Site.PageNotFoundException e) {
                        System.out.println("page down invalid - current page has no links");
                    } catch (Site.NameNotFoundException e) {
                        System.out.println("page down invalid - no match found for page ");
                    }


                    System.out.println(site.getCurrent());
                    try{
                    if(site.checkIfHasCurrentDown() == true){
                        String namePage = Input.getString("page name: ");
                        site.moveDown();
                        site.findSite(namePage);
                    }
                    }catch(Site.PageNotFoundException e){
                        System.out.println("page down invalid - current page has no links");
                    }catch(Site.NameNotFoundException e){
                        System.out.println("page down invalid - no match found for page ");
                    }
                    

                    break;
                case 3:
                    site.moveUp();
                    break;
                case 4:
                    System.out.println(site.getCurrent());
                    break;
                case 5:
                    System.out.println(site);
                    break;
                case 0:
                    break;
            }

        } while (option != 0);
    }

        }while (option != 0);
     }

}
