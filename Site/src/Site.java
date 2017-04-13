public class Site {
    
    public class PageNotFoundException extends Exception{}
    
    private class PageNode{
        private String name;
        private PageNode up;
        private PageNode across ;
        private PageNode down;
    }
    
    private PageNode home;
    private PageNode current;
    
    public Site(String homePage){
        this.home = new PageNode();
        this.home.name = homePage;
        this.current = this.home;
    }
    
    public String toString() {
        String pageDetails = new String();
        pageDetails += this.home.name + "\n";
        PageNode pageAcross = this.home.down;
        if (pageAcross == null) {
            pageDetails += "  has no page\n";
        } else {
            while (pageAcross != null) {
                pageDetails += "  " + pageAcross.name + "\n";
                pageAcross = pageAcross.across;
            }
        }
        return pageDetails;
    }
    
    public void addSite(String name) throws PageNotFoundException{
        PageNode newNode = new PageNode();
        newNode.name = name;
        if (this.home.down == null) {
            this.home.down = newNode;
        } else {
            this.addSite(newNode,this.home.down);
        }
    }
    
   private void addSite(PageNode newNode,PageNode current) throws PageNotFoundException{
       
        if (newNode.name.compareTo(current.name) == 0){
            throw new PageNotFoundException();
        }
            if (current.across == null) {
                current.across = newNode;
            } else {
                this.addSite(newNode,current.across);
            }      
        }
    
}
