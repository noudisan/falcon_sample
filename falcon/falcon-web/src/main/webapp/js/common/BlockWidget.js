var BlockWidget = {
   block : function () {
       var widowsWidth = ((($(window).width())/2)-100)+'px';
       var imagePath=getValue('#contextPath');


       $.blockUI({css:{height:'55px',paddingTop:'10px',width:'200px',border: 'none',left:widowsWidth}, message:"<img src='"+imagePath+"/images/loading.gif' style='width:150px;'><br>" + "<span style='color:red'>正在处理中...</span>"});
       $(".blockPage").css("z-index", "10000");
       $(".blockOverlay").css("z-index", "9000");
   },

   unBlock : function () {
       $.unblockUI();
       $(".blockPage").css("z-index", "0 !important");
       $(".blockOverlay").css("z-index", "0");
   }
}
