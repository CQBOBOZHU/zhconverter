package com.github.nobodxbodon.zhconverter;

import static com.github.program_in_chinese.junit4_in_chinese.断言.相等;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.github.nobodxbodon.zhconverter.简繁转换类.目标;

public class 自定义词典测试类 {

  private static List<String> 词典 = Arrays.asList("乾兴", "乾坤");
  
  private static final String 乾兴 =
      "--(961-1023)字平仲，华州下(今陕西渭南)人。太平兴国五年(1980)进士。累官至中书侍郎同中书下平章事。二次罢相，封莱国公。乾兴初，为丁谓所，贬雷州司户参军，卒于贬所。有《寇莱公集》。存词五首，以《江南春》较著名。";

  @Test
  public void 静态方法测试() {
    确认简繁体互转("简单", "簡單");
    确认简繁体互转("曹操", "曹操");
    确认简繁体互转("赵云", "趙雲");
    确认简繁体互转("岳飞", "岳飛");

    // TODO: issue #1. 不知此字繁体是什么?
    确认简繁体互转("暰", "暰");
    
    // issue #4 简体转繁体时，“机械”一起的时候不能转换，但只有一个"机"字可以转换。
    确认简繁体互转("机", "機");
    确认简繁体互转("机械", "機械");

    // issue #5
    确认简繁体互转("一哄而散", "一鬨而散");
    
    // https://github.com/NLPchina/nlp-lang/issues/23
    确认简繁体互转("土著");
    确认简繁体互转("乾坤");
    
    // https://github.com/NLPchina/nlp-lang/issues/24
    确认简繁体互转("尼日利亚", "尼日利亞");
    确认简繁体互转("巴基斯坦");
    确认简繁体互转("厄瓜多尔", "厄瓜多爾");
    
    确认简繁体互转("有背光的机械式键盘", "有背光的機械式鍵盤");
    
    // https://github.com/nobodxbodon/zhconverter/issues/5
    确认简繁体互转("叶子", "葉子");

    // https://github.com/nobodxbodon/zhconverter/issues/6
    确认简繁体互转("发财", "發財");
    确认简繁体互转("理发", "理髮");
    // TODO: 需要修改算法, 考虑分词匹配
    // 确认简繁体互转("发财理发", "發財理髮");

    确认简繁体互转("叶问", "葉問");

    // 其他库的测试用例 https://www.npmjs.com/package/simplebig
    确认简繁体互转("东加拿大", "東加拿大");
    确认简繁体互转("太古遗产", "太古遺產");
    确认简繁体互转("繁体中文", "繁體中文");
    确认简繁体互转("香港动漫", "香港動漫");
    确认简繁体互转("夜莺工作室", "夜鶯工作室");

    // https://www.npmjs.com/package/jiantifanti
    确认简繁体互转("这个", "這個");

    // https://www.npmjs.com/package/zh2cht
    确认简繁体互转("简体到繁体", "簡體到繁體");

    确认简繁体互转("冉闵", "冉閔");

    // https://github.com/program-in-chinese/zhconverter/issues/3
    确认简繁体互转("乾坤");
    确认简繁体互转("乾宁", "乾寧");
  }
  
  @Test
  public void 例外词测试() {
    相等(乾兴, 忽略特殊词(乾兴, 目标.简体));
    相等("酒里乾坤大", 忽略特殊词("酒里乾坤大", 目标.简体));
  }

  private static String 忽略特殊词(String 原文本, 目标 简繁) {
    Map<String, Integer> 特殊词位置 = new HashMap<String, Integer>();
    for (String 词 : 词典) {
      int 位置 = 原文本.indexOf(词);
      if (位置 != -1) {
        特殊词位置.put(词, 位置);
      }
    }
    String 初步转换 = 简繁转换类.转换(原文本, 简繁);
    if (特殊词位置.isEmpty()) {
      return 初步转换;
    } else {
      StringBuilder 还原 = new StringBuilder(初步转换);
      for (String 特殊词 : 特殊词位置.keySet()) {
        int 位置 = 特殊词位置.get(特殊词);
        还原.replace(位置, 特殊词.length() + 位置, 特殊词);
      }
      return 还原.toString();
    }
  }

  private void 确认简繁体互转(String 文本) {
    确认简繁体互转(文本, 文本);
  }
  
  private void 确认简繁体互转(String 简体文本, String 繁体文本) {
    相等(繁体文本, 忽略特殊词(简体文本, 目标.繁体));
    相等(简体文本, 忽略特殊词(繁体文本, 目标.简体));
  }
}
