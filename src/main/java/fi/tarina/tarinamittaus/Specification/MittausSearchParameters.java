package fi.tarina.tarinamittaus.Specification;


public class MittausSearchParameters {

    private String  searchKeyword;
    private Double squareArea;
    private Integer constructionYear;

    public MittausSearchParameters() {
    }

    public MittausSearchParameters(String searchKeyword, Double squareArea, Integer constructionYear) {
        this.searchKeyword = searchKeyword;
        this.squareArea = squareArea;
        this.constructionYear = constructionYear;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public Double getSquareArea() {
        return squareArea;
    }

    public void setSquareArea(Double squareArea) {
        this.squareArea = squareArea;
    }

    public Integer getConstructionYear() {
        return constructionYear;
    }

    public void setConstructionYear(Integer constructionYear) {
        this.constructionYear = constructionYear;
    }

    @Override
    public String toString() {
        return "MittausSearchParameters{" +
                "searchKeyword='" + searchKeyword + '\'' +
                ", squareArea=" + squareArea +
                ", constructionYear=" + constructionYear +
                '}';
    }
}
