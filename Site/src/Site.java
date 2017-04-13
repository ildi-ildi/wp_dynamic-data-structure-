public class Site {
    
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
}
