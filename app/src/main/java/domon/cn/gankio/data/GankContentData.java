package domon.cn.gankio.data;

import java.util.List;

/**
 * Created by Domon on 16-8-10.
 */
public class GankContentData {

    /**
     * category : ["Android","休息视频","iOS","福利"]
     * error : false
     * results : {"Android":[{"_id":"56efcc4c677659164d5643ca","createdAt":"2016-03-21T18:26:20.122Z","desc":"android 加载动画","publishedAt":"2016-08-10T11:37:13.981Z","source":"chrome","type":"Android","url":"https://github.com/ybq/AndroidSpinKit","used":true,"who":"ybq"},{"_id":"56fce1dd67765933d8be9188","createdAt":"2016-03-31T16:37:49.476Z","desc":"Welcome Coordinator for Android","publishedAt":"2016-08-10T11:37:13.981Z","source":"chrome","type":"Android","url":"https://github.com/txusballesteros/welcome-coordinator","used":true,"who":"Masayuki Suda"},{"_id":"57a9c919421aa90b3aac1edb","createdAt":"2016-08-09T20:14:17.385Z","desc":"Android任意添加贴纸，支持添加Bitmap和Drawable","publishedAt":"2016-08-10T11:37:13.981Z","source":"web","type":"Android","url":"https://github.com/wuapnjie/StickerView","used":true,"who":"FlyingSnowBean"},{"_id":"57a9fa9a421aa90b38e829c6","createdAt":"2016-08-09T23:45:30.933Z","desc":"iOS 中的 block 是如何持有对象的","publishedAt":"2016-08-10T11:37:13.981Z","source":"web","type":"Android","url":"http://draveness.me/block-retain-object/","used":true,"who":"Draveness"},{"_id":"57aa7241421aa90b3aac1edd","createdAt":"2016-08-10T08:16:01.833Z","desc":"漂亮的变换引导效果","publishedAt":"2016-08-10T11:37:13.981Z","source":"chrome","type":"Android","url":"https://github.com/eoinfogarty/Onboarding","used":true,"who":"代码家"},{"_id":"57aa72eb421aa90b3aac1ede","createdAt":"2016-08-10T08:18:51.657Z","desc":"Button 拉长展开效果","publishedAt":"2016-08-10T11:37:13.981Z","source":"chrome","type":"Android","url":"https://github.com/daniel-martins-IR/MagicButton","used":true,"who":"代码家"},{"_id":"57aa7d5c421aa90b3aac1edf","createdAt":"2016-08-10T09:03:24.470Z","desc":"Java8 实用字符串操作库","publishedAt":"2016-08-10T11:37:13.981Z","source":"chrome","type":"Android","url":"https://github.com/shekhargulati/strman-java","used":true,"who":"代码家"}],"iOS":[{"_id":"57aa8895421aa90b35e1f408","createdAt":"2016-08-10T09:51:17.946Z","desc":"iOS 图片裁切辅助工具库","publishedAt":"2016-08-10T11:37:13.981Z","source":"chrome","type":"iOS","url":"https://github.com/3tinkers/TKImageView","used":true,"who":"3tinkers"},{"_id":"57aa89b1421aa90b38e829c7","createdAt":"2016-08-10T09:56:01.789Z","desc":"Swift 实现的圆形头像小库子","publishedAt":"2016-08-10T11:37:13.981Z","source":"chrome","type":"iOS","url":"https://github.com/dkalaitzidis/SwiftyAvatar","used":true,"who":"代码家"},{"_id":"57aa8bd6421aa90b35e1f409","createdAt":"2016-08-10T10:05:10.118Z","desc":"做的很有意思的一个同步辅助工具库，可以做一些好玩儿的事情，比如本地配置和服务器配置同步，本地文件实时同步到服务器，或者可以本地书写长文实时同步到手机等等","publishedAt":"2016-08-10T11:37:13.981Z","source":"chrome","type":"iOS","url":"https://github.com/krzysztofzablocki/KZFileWatchers","used":true,"who":"代码家"}],"休息视频":[{"_id":"57aa7e77421aa90b3aac1ee0","createdAt":"2016-08-10T09:08:07.150Z","desc":"林纳斯\u2022托瓦兹:Linux 操作系统之父","publishedAt":"2016-08-10T11:37:13.981Z","source":"web","type":"休息视频","url":"http://v.youku.com/v_show/id_XMTY1MjYyNjkyOA==.html","used":true,"who":"龙龙童鞋"}],"福利":[{"_id":"57aa8c8a421aa90b3aac1ee1","createdAt":"2016-08-10T10:08:10.911Z","desc":"8-10","publishedAt":"2016-08-10T11:37:13.981Z","source":"chrome","type":"福利","url":"http://ww2.sinaimg.cn/large/610dc034jw1f6ofd28kr6j20dw0kudgx.jpg","used":true,"who":"代码家"}]}
     */

    private boolean error;
    private GankCatagoryInfoData results;
    private List<String> category;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public GankCatagoryInfoData getResults() {
        return results;
    }

    public void setResults(GankCatagoryInfoData results) {
        this.results = results;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }
}
