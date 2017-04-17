
public class Site {

    public class PageNotFoundException extends Exception {
    }

    public class NameNotFoundException extends Exception {
    }

    public class NameNotUniqueException extends Exception {
    }

    public class TopPageException extends Exception {
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

        String siteDetails = new String();
        siteDetails += this.getSite(this.home, 0);

        return siteDetails;
    }

    private String getSite(PageNode current, Integer level) {

        String details = new String();
        Integer x = 1;

        if (current != null) {

            details += current.name + "\n";

            if (current.down != null) {

                level++;
                for (Integer i = 0; i < level; i++) {
                    details += "   ";
                }

                details += this.getSite(current.down, level);

                level = level - x;
            }

            if (current.across != null) {
                x++;

                for (Integer i = 0; i < level; i++) {
                    details += "   ";
                }

                details += this.getSite(current.across, level);

            }
        }
        return details;
    }

    public void addPage(String name) throws NameNotUniqueException {

        if (isUnique(name) == true) {
            throw new NameNotUniqueException();
        }

        PageNode newNode = new PageNode();
        newNode.name = name;
        if (this.currentPage.down == null) { //mda...
            this.currentPage.down = newNode;
            newNode.up = this.currentPage;
        } else {
            this.addPage(newNode, this.currentPage.down);
        }
    }

    private void addPage(PageNode newNode, PageNode current) {

        if (current.across != null) {
            current = current.across;
            this.addPage(newNode, current);
        } else {
            current.across = newNode;
            newNode.up = this.currentPage;
        }
    }

    public Boolean isUnique(String pageName) {
        return this.isUnique(pageName, this.home);
    }

    private Boolean isUnique(String pageName, PageNode next) { //working version

        Boolean found = false;
        PageNode currentNext = next;

        if (next != null) {

            if (pageName.compareTo(next.name) == 0) {
                found = true;

            } else {

                if (next.down != null) {
                    String nametest1 = next.down.name;
                    found = this.isUnique(pageName, next.down);
                }

                if (!found && next.across != null) {
                    String nametest = next.across.name;
                    found = this.isUnique(pageName, next.across);
                }

            }
        } else {
            found = false;
        }

        return found;
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

        String found = " ";
        if (currentPage != null) {

            if (siteName.compareTo(currentPage.name) == 0) {
                this.currentPage = currentPage;
                found = currentPage.name;
            } else {
                found = this.findSite(siteName, currentPage.across);
            }

        } else {
            try {
                moveUp();
                throw new NameNotFoundException();
            } catch (TopPageException e) {
                System.out.println("page up invalid - home page ");
            }
        }

        return found;
    }

    public void moveUp() throws TopPageException {
        if (this.currentPage == this.home) { //top home page
            throw new TopPageException();
        } else {
            this.currentPage = this.currentPage.up;
        }
    }

}
