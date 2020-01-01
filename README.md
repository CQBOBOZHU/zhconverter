[![Build Status](https://travis-ci.org/program-in-chinese/zhconverter.svg?branch=master)](https://travis-ci.org/program-in-chinese/zhconverter)

# zhconverter
中文字符简繁体互相转换, 基于https://code.google.com/archive/p/java-zhconverter/
据项目描述来源于MediaWiki.

- 转换规则很简单, 完全不进行分词.
- 如果输入文本不是单字, 如果在对应表中有完全匹配, 就返回对应的文本; 不然就逐字按照单字转换.

修改匹配算法后, 解决了原有用户报告的两个问题:

- https://github.com/nobodxbodon/zhconverter/issues/3 
- https://github.com/nobodxbodon/zhconverter/issues/2

已提交到Maven Central Repository:
```
<dependency>
  <groupId>com.github.nobodxbodon</groupId>
  <artifactId>zhconverter</artifactId>
  <version>0.0.5</version>
</dependency>
```
发布过程中的一些问题及解决请详见: [在Maven Central发布中文API的Java库](https://zhuanlan.zhihu.com/p/28024364)

V2EX对此项目的反馈帖: [第一次见以汉字命名的 Java 类](https://www.v2ex.com/t/480623)

LGPL许可证
