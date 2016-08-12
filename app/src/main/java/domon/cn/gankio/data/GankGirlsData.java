package domon.cn.gankio.data;

import java.util.List;

/**
 * Created by Domon on 16-8-12.
 */
public class GankGirlsData {


    /**
     * error : false
     * results : [{"_id":"57ad4023421aa949ef961f4b","createdAt":"2016-08-12T11:18:59.569Z","desc":"8-12","publishedAt":"2016-08-12T11:39:10.578Z","source":"chrome","type":"福利","url":"http://ww3.sinaimg.cn/large/610dc034jw1f6qsn74e3yj20u011htc6.jpg","used":true,"who":"代码家"},{"_id":"57abf5ac421aa93fa66e8406","createdAt":"2016-08-11T11:49:00.660Z","desc":"8.11","publishedAt":"2016-08-11T12:07:01.963Z","source":"chrome","type":"福利","url":"http://ww2.sinaimg.cn/large/610dc034jw1f6pnw6i7lqj20u00u0tbr.jpg","used":true,"who":"代码家"},{"_id":"57aa8c8a421aa90b3aac1ee1","createdAt":"2016-08-10T10:08:10.911Z","desc":"8-10","publishedAt":"2016-08-10T11:37:13.981Z","source":"chrome","type":"福利","url":"http://ww2.sinaimg.cn/large/610dc034jw1f6ofd28kr6j20dw0kudgx.jpg","used":true,"who":"代码家"},{"_id":"57a94a33421aa90b38e829be","createdAt":"2016-08-09T11:12:51.255Z","desc":"8.9","publishedAt":"2016-08-09T11:30:16.672Z","source":"chrome","type":"福利","url":"http://ww1.sinaimg.cn/large/610dc034jw1f6nbm78pplj20dw0i2djy.jpg","used":true,"who":"代码家"},{"_id":"57a7eae4421aa90b35e1f3ec","createdAt":"2016-08-08T10:13:56.28Z","desc":"8.8","publishedAt":"2016-08-08T11:47:45.839Z","source":"chrome","type":"福利","url":"http://ww3.sinaimg.cn/large/610dc034jw1f6m4aj83g9j20zk1hcww3.jpg","used":true,"who":"代码家"},{"_id":"57a4056c421aa91e2606478d","createdAt":"2016-08-05T11:18:04.807Z","desc":"8.5","publishedAt":"2016-08-05T11:31:58.293Z","source":"chrome","type":"福利","url":"http://ww4.sinaimg.cn/large/610dc034jw1f6ipaai7wgj20dw0kugp4.jpg","used":true,"who":"代码家"},{"_id":"57a159ee421aa91e2606476b","createdAt":"2016-08-03T10:41:50.299Z","desc":"8-3","publishedAt":"2016-08-03T11:12:47.159Z","source":"chrome","type":"福利","url":"http://ww3.sinaimg.cn/large/610dc034jw1f6gcxc1t7vj20hs0hsgo1.jpg","used":true,"who":"代码家"},{"_id":"579ff9d0421aa90d39e709be","createdAt":"2016-08-02T09:39:28.23Z","desc":"8.2","publishedAt":"2016-08-02T11:40:01.363Z","source":"chrome","type":"福利","url":"http://ww4.sinaimg.cn/large/610dc034jw1f6f5ktcyk0j20u011hacg.jpg","used":true,"who":"代码家"},{"_id":"579eb4b4421aa90d2fc94ba0","createdAt":"2016-08-01T10:32:20.10Z","desc":"8.1","publishedAt":"2016-08-01T12:00:57.45Z","source":"chrome","type":"福利","url":"http://ww1.sinaimg.cn/large/610dc034jw1f6e1f1qmg3j20u00u0djp.jpg","used":true,"who":"代码家"},{"_id":"579ab0a8421aa90d36e960b4","createdAt":"2016-07-29T09:26:00.838Z","desc":"7.29","publishedAt":"2016-07-29T09:37:39.219Z","source":"chrome","type":"福利","url":"http://ww3.sinaimg.cn/large/610dc034jw1f6aipo68yvj20qo0qoaee.jpg","used":true,"who":"代码家"}]
     */

    private boolean error;
    /**
     * _id : 57ad4023421aa949ef961f4b
     * createdAt : 2016-08-12T11:18:59.569Z
     * desc : 8-12
     * publishedAt : 2016-08-12T11:39:10.578Z
     * source : chrome
     * type : 福利
     * url : http://ww3.sinaimg.cn/large/610dc034jw1f6qsn74e3yj20u011htc6.jpg
     * used : true
     * who : 代码家
     */

    private List<ResultsEntity> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsEntity> getResults() {
        return results;
    }

    public void setResults(List<ResultsEntity> results) {
        this.results = results;
    }

    public static class ResultsEntity {
        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }
    }
}
