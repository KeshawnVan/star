package star.bean;

import java.util.Date;
import java.util.List;

/**
 * @author keshawn
 * @date 2018/3/8
 */
public class Target {
    private Long id;

    private String name;

    private List<Integer> lists;

    private Double dd;

    private Date time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public List<Integer> getLists() {
        return lists;
    }

    public void setLists(List<Integer> lists) {
        this.lists = lists;
    }

    public Double getDd() {
        return dd;
    }

    public void setDd(Double dd) {
        this.dd = dd;
    }
}
