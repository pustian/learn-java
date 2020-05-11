

# openjdk

### openjdk7u

http://hg.openjdk.java.net/jdk6/jdk6/raw-file/tip/README-builds.html

#### 获取代码

```bash
# centos
yum install -y hg
# ubuntu
hg clone http://hg.openjdk.java.net/jdk7u/jdk7u openjdk7u
cd openjdk7
sh get_source.sh
```

#### 依赖安装

```bash
# jdk1.6 安装
# yum install java-1.6.0-openjdk 
# ant 安装
# yum install ant
# FreeType 字体错误
yum install freetype-devel 
# Cups header file
yum install cups-devel
# 开放主题
yum install openmotif openmotif-devel
# yum install libX* gcc-g++ make
yum groupinstall '开发工具'
# 安装ALSA声卡驱动  apt-get install libasound2-dev
yum install  alsa-tools alsa-lib-devel alsa-utils
```

#### 环境设置

> 注意目录 OPENJDK_HOME
>
> ALT_BOOTDIR
>
> ALT_JDK_IMPORT_PATH

```bash
export OPENJDK_HOME=/root/openjdk7u           # 解压的源码包位置

# 环境变量设置 #语言选项，这个必须设置，否则编译好后会出现一个HashTable的NPE错
export LANG=C

# Bootstrap JDK的安装路径。必须设置
export ALT_BOOTDIR=/usr/local/java            # 机器上现有的JDK位置, JAVA_HOME
export ALT_JDK_IMPORT_PATH=/usr/local/java

# 允许自动下载依赖
export ALLOW_DOWNLOADS=false

#并行编译的线程数，设置为和CPU内核数量一致即可
export HOTSPOT_BUILD_JOBS=4                   # 编译线程数, 与CPU核数一致即可
export ALT_PARALLEL_COMPILE_JOBS=4

#使用预编译头文件，不加这个编译会更慢一些
export USE_PRECOMPILED_HEADER=true

export DEV_ONLY=true
#比较本次build出来的映像与先前版本的差异。这对我们来说没有意义，
#必须设置为false，否则sanity检查会报缺少先前版本JDK的映像的错误提示。
#如果已经设置dev或者DEV_ONLY=true，这个不显式设置也行
export SKIP_COMPARE_IMAGES=true

#要编译的内容
# jaxp,jaxws, corba 三个组件编译时需要从网络上下载相关依赖包, 可以设置为false 不影响一般使用
# get_source.sh 好像是下载
export BUILD_LANGTOOLS=true
export BUILD_JAXP=false
export BUILD_JAXWS=false
export BUILD_CORBA=false
export BUILD_HOTSPOT=true
export BUILD_JDK=true

#要编译的版本
#export SKIP_DEBUG_BUILD=false
#export SKIP_FASTDEBUG_BUILD=true
#export DEBUG_NAME=debug

# 把它设置为false可以避开javaws和浏览器Java插件之类的部分的build
export BUILD_DEPLOY=false 

#把它设置为false就不会build出安装包。因为安装包里有些奇怪的依赖，
#但即便不build出它也已经能得到完整的JDK映像，所以还是别build它好了
export BUILD_INSTALL=false

#编译结果所存放的路径
export ALT_OUTPUTDIR=$OPENJDK_HOME/build      # 编译输出目录

#这两个环境变量必须去掉  make脚本中会报ERROR
unset JAVA_HOME
unset CLASSPATH

# 验证
make sanity 

# 编译
make |tee build.log
```

#### FQA:

##### Q1：

```
不支持 kernel 5.X 版本
```

A1：

```bash
切换低版本的kernel
kernel 3.X
```

##### Q2:

```bash
/usr/bin/ld: cannot find -lstdc++
collect2: error: ld returned 1 exit status
ln: failed to access 'libjvm.so.1': Too many levels of symbolic links
/usr/bin/chcon: failed to get security context of 'libjvm.so': Too many levels of symbolic links
ERROR: Cannot chcon libjvm.so
/usr/bin/objcopy --only-keep-debug libjvm.so libjvm.debuginfo
/usr/bin/objcopy: Warning: could not locate 'libjvm.so'.  reason: Too many levels of symbolic links
make[6]: *** [libjvm.so] Error 1
make[5]: *** [the_vm] Error 2
make[6]: Leaving directory `/root/openjdk7u/build/hotspot/outputdir/linux_amd64_compiler2/product'
make[5]: Leaving directory `/root/openjdk7u/build/hotspot/outputdir/linux_amd64_compiler2/product'
make[4]: *** [product] Error 2
make[4]: Leaving directory `/root/openjdk7u/build/hotspot/outputdir'
make[3]: *** [generic_build2] Error 2
make[3]: Leaving directory `/root/openjdk7u/hotspot/make'
make[2]: *** [product] Error 2
make[2]: Leaving directory `/root/openjdk7u/hotspot/make'
make[1]: *** [hotspot-build] Error 2
make[1]: Leaving directory `/root/openjdk7u'
make: *** [build_product_image] Error 2
```

### A2:

```bash
yum install gcc-c++ libstdc++-devel
yum install systemtap-sdt-devel
```





```bash
make clean && make make sanity && make |tee build.log
原因是libjvm.so 相互指向
# 示例
    ln -s x.out x.out  
    cat x.out  
    # 会输出 cat: x.out: Too many levels of symbolic links  
ls -l  ./build/hotspot/outputdir/linux_amd64_compiler2/product/lib*
rm -f ./build/hotspot/outputdir/linux_amd64_compiler2/product/libjvm.so.1
rm -f ./build/hotspot/outputdir/linux_amd64_compiler2/product/libjvm.so
cp /usr/local/java/jre/lib/amd64/server/libjvm.so  ./build/hotspot/outputdir/linux_amd64_compiler2/product/libjvm.so
```



 the [Bootstrap JDK](http://hg.openjdk.java.net/jdk6/jdk6/raw-file/tip/README-builds.html#bootjdk), set `ALT_BOOTDIR`. 

> 1. [Optional Import JDK](http://hg.openjdk.java.net/jdk6/jdk6/raw-file/tip/README-builds.html#importjdk), set `ALT_JDK_IMPORT_PATH`. 
> 2. Install the  [Sun Studio Compilers](http://hg.openjdk.java.net/jdk6/jdk6/raw-file/tip/README-builds.html#studio), set [`ALT_COMPILER_PATH`](http://hg.openjdk.java.net/jdk6/jdk6/raw-file/tip/README-builds.html#ALT_COMPILER_PATH). 
> 3. Install the [CUPS Include files](http://hg.openjdk.java.net/jdk6/jdk6/raw-file/tip/README-builds.html#cups), set `ALT_CUPS_HEADERS_PATH`.  
> 4. Install [Ant 1.7.1 or newer](http://hg.openjdk.java.net/jdk6/jdk6/raw-file/tip/README-builds.html#ant), make sure it is in your PATH.  



# openjdk7

http://hg.openjdk.java.net/jdk7/jdk7/raw-file/tip/README-builds.html

https://blog.csdn.net/zmken497300/article/details/89927427

源码

```bash
http://jdk.java.net/
http://hg.openjdk.java.net/
http://hg.openjdk.java.net/jdk
http://openjdk.java.net/
```

# jdk7u

https://www.jianshu.com/p/eaa2756e93d0

https://blog.csdn.net/linsir20/article/details/99302927

## 环境准备

> 因为OpenJDK的各个组成部分有的是使用C++编译，有的是使用Java自身实现的，所以编译这些Java代码需要一个可用的JDK，官方称这个JDK为“Bootstrap JDK”
>
> OpenJDK源码中使用到Ant脚本，我使用的版本为：apache-ant-1.9.14-bin

### Bootstrap JDK

```bash
# 需要一个比这个版本低的 "启动jdk",并配置号
java -version
# 此处为1.6
ant -version
# 此处为1.9
```

### 其他依赖

```bash
yum -y groupinstall 'base'
yum -y install make
#安装linux声音框架
yum -y install alsa-lib-devel
#安装打印框架
yum -y install cups-devel
#安装X相关的库
yum -y install libXi-devel
yum -y install libX*
#安装g++
yum -y install gcc gcc-c++
#解决没有找到lstdc++的错误
yum install libstdc++-static -y

yum install freetype -y
```

源码下载

```bash
yum install hg
hg clone http://hg.openjdk.java.net/jdk7u/jdk7u60 openjdk-7-src 
cd openjdk-7-src
bash get_source.sh 
```

### make sanity检查

```bash
#要编译的版本
export SKIP_DEBUG_BUILD=false
export SKIP_FASTDEBUG_BUILD=true
export DEBUG_NAME=debug

#要编译的内容
export BUILD_LANGTOOLS=true
#export BUILD_JAXP=false
#export BUILD_JAXWS=false
#export BUILD_CORBA=false
export BUILD_HOTSPOT=true
export BUILD_JDK=true

#允许自动下载依赖
export ALLOW_DOWNLOADS=true

#并行编译的线程数， 设置为和CPU内核数量一致即可
export HOTSPOT_BUILD_JOBS=1
export ALT_PARALLEL_COMPILE_JOBS=1

#比较本次build出来的映像与先前版本的差异。 这对我们来说没有意义，
#必须设置为false， 否则sanity检查会报缺少先前版本JDK的映像的错误提示。
#如果已经设置dev或者DEV_ONLY=true， 这个不显式设置也行
export SKIP_COMPARE_IMAGES=true

#使用预编译头文件， 不加这个编译会更慢一些
export USE_PRECOMPILED_HEADER=true

#把它设置为false可以避开javaws和浏览器Java插件之类的部分的buildBUILD_DEPLOY=false
#把它设置为false就不会build出安装包。 因为安装包里有些奇怪的依赖，
#但即便不build出它也已经能得到完整的JDK映像， 所以还是别build它好了
BUILD_INSTALL=false

#编译结果所存放的路径
export ALT_OUTPUTDIR=/root/jvm/build

#这两个环境变量必须去掉，不然会有很诡异的事情发生（我没有具体查过这些"诡异的
#事情"， Makefile脚本检查到有这2个变量就会提示警告）
unset JAVA_HOME
unset CLASSPATH

make sanity 
```



```bash
yum groupinstall "Development Tools"  
yum install libXtst-devel libXt-devel libXrender-devel  -y
yum install cups-devel  -y
yum install freetype-devel  -y
yum install alsa-lib-devel  -y
yum install ccache -y
yum install freetype2 -y
```

```bash
 ./configure --with-target-bits=64 --with-boot-jdk=/usr/local/java/ \
   --with-debug-level=slowdebug --enable-debug-symbols ZIP_DEBUGINFO_FILES=0
```

