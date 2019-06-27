package com.microblog.common.module.points;

/**
 *功能描述
 * @author lgj
 * @Description 积分操作类型
 * @date 5/15/19
*/
public enum  PointsTypes {

    /***
     * 添加积分：每日签到
     */
    POINTS_ADD_TYPE_DAILY_SIGNATURE(10,1),
    /***
     * 添加积分:发布微博
     */
    POINTS_ADD_TYPE_PUBLISH_BLOG(11,2),
    /***
     * 减少积分：删除微博
     */
    POINTS_DEC_TYPE_DELETD_BLOG(12,-2),
    ;

    private Integer type;
    private Integer point;

    PointsTypes(Integer type, Integer point) {
        this.type = type;
        this.point = point;
    }

    public Integer getType() {
        return type;
    }

    public Integer getPoint() {
        return point;
    }
}

