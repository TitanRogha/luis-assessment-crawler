public class Factory {
    public static JsonLd build (String type){
        switch (type){
            case "WebSite":
                return new WebSite();

            default:
                System.out.println("No encuentro la clase");
                return null;
        }
    }
}
