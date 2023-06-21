## nexus 导出工具(增量)

```bash
java -jar nexus3-export-1.0.jar \
http://127.0.0.1:8081/nexus custom_hosted \
. \
com.unionpay.magpie \
magpie \
2.1.1.Final


# 可以不指定版本号

java -jar nexus3-export-1.0.jar \
http://127.0.0.1:8081/nexus custom_hosted \
. \
com.unionpay.magpie \
magpie


# 甚至不指定组件名称

# 可以不指定版本号

java -jar nexus3-export-1.0.jar \
http://127.0.0.1:8081/nexus custom_hosted \
. \
com.unionpay.magpie
```

## nexus 导入工具（增量）

```shell
# 在根目录下执行
sh /Users/fangyuqing/develop/up-nexus3-export/nexus-import.sh -r http://127.0.0.1:8081/nexus/repository/custom_hosted -u admin -p admin
```