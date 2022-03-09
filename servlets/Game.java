public class Game {
	private int gameId;
	private String title;
	private String developer;
    private float price;
    private int qtySold;

    public Game(gameId, title, developer, price, qtySold) {
        this.gameId = gameId;
        this.developer = developer;
        this.price = price;
        this.title = title;
        this.qtySold = qtySold;
    }


    public String getTitle() {
    	return title;
    }

    public String getDeveloper() {
        return developer;
    }

    public float getPrice() {
        return price;
    }

    public int getQtySold() {
        return qtySold;
    }

}