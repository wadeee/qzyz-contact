# 泉州一中2012届高三一班通讯录录入系统

## Docker下运行

+ 进入Dockerfile目录，构建image并查看镜像是否生成

    ```bash
    docker build -t qzyzcontact ./Dockerfile
    docker images
    ```

+ 生成并运行container映射8080端口到3000端口（3000端口可以任意更改）

    ```bash
    docker run -p 3000:8080 --name=qzyzcontact qzyzcontact
    ```

+ 打开[http://localhost:3000/]

    - 没有浏览器的情况下可以用curl打开查看是否正常运行
  
        ```shell script
        curl http://localhost:3000/
        ```


## 使用技术

+ [Apache POI](https://poi.apache.org/)

+ [Spring](https://spring.io/)

+ [FreeMaker](https://freemarker.apache.org/)
