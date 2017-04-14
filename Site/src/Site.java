
public class Site {

    public class PageNotFoundException extends Exception {
    }

    public class NameNotFoundException extends Exception {
    }

    private class PageNode {

        private String name;
        private PageNode up;
        private PageNode across;
        private PageNode down;
    }

    private PageNode home;
    private PageNode current;
    private PageNode currentPage;

    public Site(String homePage) {
        this.home = new PageNode();
        this.home.name = homePage;
        this.current = this.home;
        this.currentPage = this.home;
    }

    public String toString() {
        String pageDetails = new String();
        pageDetails += this.home.name + "\n";
        PageNode pageAcross = this.home.down;
        if (pageAcross == null) {
            pageDetails += "  has no links\n";
        } else {
            while (pageAcross != null) {
                pageDetails += "  " + pageAcross.name + "\n";
                pageAcross = pageAcross.across;
            }
        }
        return pageDetails;
    }

    public void addSite(String name) throws PageNotFoundException {
        PageNode newNode = new PageNode();
        newNode.name = name;
        if (this.home == null) { 
            this.home = newNode;
        } else {
            this.addSite(newNode, this.currentPage);
        }
    }

    private void addSite(PageNode newNode, PageNode current) throws PageNotFoundException {

        if (newNode.name.compareTo(current.name) == 0) {
            throw new PageNotFoundException();
        }
        if (this.currentPage.down == null) {
            this.currentPage.down = newNode;
            newNode.up = this.currentPage.up;
            current.up = this.currentPage.down;
        } else {
            if (current.across != null) {
                current = current.across;
                this.addSite(newNode, current);
            } else {
                current.across = newNode;
                newNode.up = this.currentPage.up;
            }
        }
    }

    public String getCurrent() {
        String currentDetails = new String();
        currentDetails += this.currentPage.name + "\n";
        PageNode pageAcross = this.currentPage.down;
        if (pageAcross == null) {
            currentDetails += "  has no link\n";
        } else {
            while (pageAcross != null) {
                currentDetails += "  " + pageAcross.name + "\n";
                pageAcross = pageAcross.across;
            }
        }

        return currentDetails;
    }

    public Boolean checkIfHasCurrentDown() throws PageNotFoundException {
        Boolean searching;
        if (this.currentPage.down == null) {
            throw new PageNotFoundException();
        } else {
            searching = true; //found
        }
        return searching;
    }

    public void moveDown() {
        this.currentPage = this.currentPage.down;
    }

    public String findSite(String siteName) throws NameNotFoundException {
        return this.findSite(siteName, this.currentPage);
    }

    private String findSite(String siteName, PageNode currentPage) throws NameNotFoundException {

        String found;
        if (currentPage != null) {

            if (siteName.compareTo(currentPage.name) == 0) {
                this.currentPage = currentPage;
                found = currentPage.name;
            } else {
                found = this.findSite(siteName, currentPage.across);
            }

        } else {
            moveUp();
            throw new NameNotFoundException();
        }

        return found;
    }

    public void moveUp() {
        this.currentPage = this.currentPage.up;
    }

}
