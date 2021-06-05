var imgs=["indexpic/pic1.png","indexpic/pic2.png","indexpic/pic3.png","indexpic/pic4.png",
            "indexpic/pic5.png","indexpic/pic6.png","indexpic/pic7.png"];
    var index=0;
    function change_pic(){
        document.getElementById("pic_change").src=imgs[index];
        index++;
        if(index >= 7){
            index=0;
        }
    }
setInterval("change_pic()",2500);