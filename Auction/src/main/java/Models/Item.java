package Models;

public class Item {
    private int id;
    private String name;
    private String image;
    private String description;
    private int buyout_price;

    public Item(int id, String name, String image, String description, int buyout_price) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.buyout_price = buyout_price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBuyout_price(int buyout_price) {
        this.buyout_price = buyout_price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public int getBuyout_price() {
        return buyout_price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;

        return id == item.getId() && name == item.getName() && image == item.getImage()
                && description == item.getDescription() && buyout_price == item.getBuyout_price();
    }
}