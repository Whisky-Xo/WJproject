<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no,maximum-scale=1.0" />
    <title>内容底级页-标题</title>
    <style>
        *{ padding:0; margin:0; list-style:none;font-family: '黑体'}
        html{background: #efeff4;}
        ul,li{list-style: none;}
        a{text-decoration: none}
        section{margin-top: .18rem; font-size: .15rem;}
        div.box{
            background: #fff;
          /*  height:.43rem;
            width: 3.6rem;*/
            border-bottom: 1px solid #d9d9d9;
            border-top: 1px solid #d9d9d9;
            /*padding:0  .14rem;*/
            padding:.1rem  .14rem;
            text-align: left;
            margin-bottom: .15rem;
        }

        .intro p{
            width: 97%;
            float: left;
        }

       .head{
            background: url('img/head1.jpg');
            display: inline-block;
            width:.38rem;
            height:.35rem;
            -webkit-background-size: contain;
            background-size: contain;
            float: left;
            margin-right: .19rem;
       }
        .head span{
            float: left;
        }

        .box_text{
            background: #fff;
           /* border-top: 1px solid #d9d9d9;*/
            padding: .04rem 0;
            color: #666666;
            line-height: .18rem;
        }
        .box_title{
           /* margin-bottom: .1rem;*/
        }
        hr{
            width: 3.59rem;
            height:1px;
            border:none;
            border-top:1px solid #d9d9d9;
            margin:.08rem 0;
        }
        .zan{
            height:.24rem;
           font-size: .18rem;
            color: #666666;
            text-align: right;
            line-height: .24rem;
        }
        .zan img{
            width: .15rem;
            height:.18rem;
            border-right: 20px solid transparent;
            -webkit-filter: drop-shadow(red 20px 0);
            filter: drop-shadow(red 20px 0);

        }
        .zan .unit{
            float: left;
            font-size: .15rem;
            color: #999999;
        }
        .article_pic{
            width:100%;
            height:1.94rem;
        }
        .icon{
            display: inline-block;
            width: .15rem;
            height:.18rem;
            overflow: hidden;
            line-height: .32rem;
        }
       .dislike{
           height: .22rem;
           line-height: .32rem;
        }
        .icon-del {
            background: url('img/hao.png') no-repeat ;
            background-size: contain;
        }
        .icon-dislike {
            background: url('img/cha.png') no-repeat ;
            background-size: contain;
        }
        .changeColor {
          position: relative;
            left: -20px;
            border-right: 20px solid transparent;
            -webkit-filter: drop-shadow(#44cc5b 20px 0);
            filter: drop-shadow(#44cc5b 20px 0);
        }
    </style>
    <script>
        (function(){
            function change(){
                var oHtml=document.querySelector('html');
                oHtml.style.fontSize=document.documentElement.clientWidth/375*100+'px';
            }

            change();
            window.addEventListener('resize',change,false);
        })();
        //点击事件
        window.addEventListener('DOMContentLoaded',function(){
            var oBox=document.querySelectorAll('.icon > .icon-del');
            var dislike=document.querySelectorAll('.dislike > .icon-dislike');
            var toggle=true;
            var toggle2=true;
            var index=0
            for(var i=0 ;i<oBox.length;i++){
                oBox[i].index=i
                dislike[i].index=i
                oBox[i].toggle=true;
                dislike[i].toggle2=true;

                oBox[i].addEventListener('touchstart',function(ev){
                    if(dislike[this.index].toggle2==true || (dislike[this.index].toggle2==false && this.toggle == false )) {
                        if (this.toggle == true) {
                            this.setAttribute('class', 'icon icon-del changeColor')
                        } else {
                            this.setAttribute('class', 'icon icon-del')
                        }
                    }else{
                        return
                    }
                    this.toggle= !this.toggle;
                    function fnEnd(){
                        document.removeEventListener('touchend',fnEnd,false);
                    }

                    document.addEventListener('touchend',fnEnd,false);
                    ev.preventDefault();
                },false);

                dislike[i].addEventListener('touchstart',function(ev){
                    if(this.toggle2==true && oBox[this.index].toggle==true){
                        this.setAttribute('class','icon icon-dislike changeColor')
                    }else{
                        this.setAttribute('class','icon icon-dislike')
                    }

                    this.toggle2= !this.toggle2;
                    function fnEnd(){
                        document.removeEventListener('touchend',fnEnd,false);
                    }


                    document.addEventListener('touchend',fnEnd,false);
                    ev.preventDefault();
                },false);
            }
        },false);

    </script>
</head>
<body>
<header></header>
<section>
    <div class="box">
        <div class="box_title">
            <span class="head"><img src="${content.userPic}" style="width:.38rem;height:.35rem"/></span>
            <span style="font-size: .16rem;line-height:.2rem;">${content.username}</span><br/>
            <span style="font-size: .12rem;color: #999999;">45分钟前</span>

        </div>
        <hr/>
        <p class="title" style="font-size: .16rem;color: #333;margin-bottom: .1rem">${content.title}</p>
        <#if content.pic?? >
        <img class="article_pic" src="http://ks3-cn-beijing.ksyun.com/aaread/${content.pic}"/>
        </#if>
        <p class="box_text">
          ${content.content}
          </p>

        <hr/>
        <div class="zan ">
            <span class="unit"> ${content.topicName!'' }</span>
            ￥32
            <span class="icon"><span class="icon icon-del"></span></span>
            |
            <span  class="icon dislike"><span class="icon icon-dislike"></span></span>
        </div>
    </div>
</section>
</body>
</html>