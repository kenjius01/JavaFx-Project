package sample;

public class Information {
    private String prdCode;
    private String prdName;
    private int price;
    private byte[] img;

    public Information() {

    }
    public Information(String prdCode, String prdName, int price, byte[] img) {
        this.img = img;
        this.prdCode = prdCode;
        this.prdName = prdName;
        this.price = price;
    }

    public byte[] getImg() {
        return img;
    }

    public int getPrice() {
        return price;
    }

    public String getPrdCode() {
        return prdCode;
    }

    public String getPrdName() {
        return prdName;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public void setPrdCode(String prdCode) {
        this.prdCode = prdCode;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setPrdName(String prdName) {
        this.prdName = prdName;
    }
}
