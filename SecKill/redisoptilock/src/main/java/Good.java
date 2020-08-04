public class Good {
    /**
     * id:主键id.
     */
    private int id;

    /**
     * status:商品状态：1未下单、2已下单.
     */
    private int status;

    /**
     * name:商品名称.
     */
    private String name;

    /**
     * version:商品数据版本号.
     */
    private int version;

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(int status){
        this.status = status;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setVersion(int version){
        this.version = version;
    }

    public int getId(){
        return this.id;
    }

    public int getStatus(){
        return this.status;
    }

    public String getName(){
        return this.name;
    }

    public int getVersion(){
        return this.version;
    }

}
