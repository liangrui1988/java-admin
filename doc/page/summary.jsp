<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>团队提成分析</title>

	<meta name="decorator" content="default"/>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
	<link rel="stylesheet" type="text/css" href="/static/lost/css/semantic/semantic.css">
	<link rel="stylesheet" type="text/css" href="/static/lost/css/semantic-datepicker.css">
	<link rel="stylesheet" type="text/css" href="/static/lost/css/main.css">
	<script src="/static/lost/js/semantic/semantic.js"></script>
	<script src="/static/lost/js/moment/moment-with-locales.js"></script>
	<script src="/static/lost/js/semantic-calendar.js"></script>
	<script src="/static/lost/js/semantic-datepicker.js"></script>
	<script src="/static/lost/js/Cockpit.js"></script>
	<!-- ECharts单文件引入 -->
	<script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
	
	<style type="text/css">
	.ui.selection.dropdown{
	 width: 260px;	}
	</style>
</head>


<body>
  <nav>团队提成分析</nav>
    <div class="ui grid">
        <div class="two column row cockpit-nav">
            <div class="column ui form">
                <div class="inline fields">
                    <label for="radio1">分析时段</label>
                    <div class="field" id="_thisMethod">
                        <div class="ui radio checkbox">
                            <input type="radio" name="radio1" checked="" value="b" tabindex="0" class="hidden" >
                            <label>本月</label>
                        </div>
                    </div>
                    
                    &nbsp; &nbsp; &nbsp; &nbsp;
                    <div class="field" id="_prveMethod">
                        <div class="ui radio checkbox">
                            <input type="radio" name="radio1" tabindex="0" value="s" class="hidden"  >
                            <label>上月</label>
                        </div>
                    </div>
                    <!-- dropdown -->
<!--               	选择月份:	<input name="feeDay" type="text" readonly="readonly" maxlength="20" class="input-small Wdate"	onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:true});"/> -->
                    <div class="ui selection dropdown" tabindex="0">
                        <input type="hidden" name="selection">
                        <i class="dropdown icon"></i>
                        <div class="default text">
                           	<input name="feeDay" id="_feeDay" type="text" readonly="readonly" placeholder="选择月份" maxlength="20" 
                           	class="input-small Wdate" style="width: 250px;border: none;"	onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:true,onpicked:showClickDate});"/>
                        </div>
<!--                         <div class="menu transition hidden" tabindex="-1"> -->
<!--                         </div> -->
                    </div>

					<select id="_teme_selection" class="ui selection dropdown"  style="width: 300px" tabindex="0" >
						<option value="0">所有团队</option>
					</select>
<!-- 					<div class="ui selection dropdown" tabindex="0" > -->
<!-- 						         <input type="hidden" name="selection" id="_teme_selection"> -->
<!-- 						          <i class="dropdown icon"></i> -->
<!-- 						       <div class="default text" data-value="all" id="_teme_selection_div">所有团队</div> -->
<!-- 					 </div> -->
				</div>
            </div>
            <div class="column">
                <div class="column ui form">
                    <div class="grouped inline fields">
                        <label for="radio2">分析层次</label>
                        <div class="field">
                            <div class="ui radio"  id="tc_fl_div">
                                <input type="radio" name="hot-deals_x" id="_tc_fl_checkbox" value="tcfl">
                                <label>提成分类</label>
                            </div>
                        </div>
                        <div class="field" id="rj_hot_deals" >
                            <div class="ui radio">
                                <input type="radio" name="hot-deals_x" id="rj_hot_deals_check" value="rj">
                                <label>人均</label>
                            </div>
                        </div>
                        <div class="field" id="_hb_div">
                            <div class="ui checkbox">
                                <input type="radio" name="hot-deals_hb_x" id="hot-deals_hb_x" value="hb">
                                <label>环比</label>
                            </div>
                        </div>
                    </div>
                  <div class="refresh"><a href="">刷新</a></div>
                </div>
            </div>
        </div>
        <!-- /.nav -->
        <div class="two column row">
            <div class="column">
                <div class="ctb-ans">
                    <ul>
                        <li>
                            <hgroup>全国总提成</hgroup>
                            <p><span id="_qg_tc"></span></p>
                        </li>
                        <li>
                            <hgroup>最高个人提成</hgroup>
                            <p><span id="_zg_tc"></span></p>
                        </li>
                        <li>
                            <hgroup>人均提成</hgroup>
                            <p><span id="_gr_tc"></span></p>
                            <!-- <span>占比 45%</span> -->
                        </li>
                        <li>
                            <hgroup>最低个人提成</hgroup>
                            <p><span id="_zd_tc"></span></p>
                            <!-- <span>占比 45%</span> -->
                        </li>
                    </ul>
                </div>
            </div>
            <div class="column">
               <div id="barMain-f" style="height:320px"></div>
               <div lr-data-enable="1" id="barMain-f-avg" style="height:320px;display:none;">xxxx</div>
                
            </div>
        </div>
        <!-- /.content -->
    </div>
    <section class="table_margin">
<!--         <table class="ui celled structured table" id="summary_table"> -->
<!--             <thead> -->
<!--                 <tr> -->
<!--                     <th>序号</th> -->
<!--                     <th>销售人员</th> -->
<!--                     <th>总提成（元）</th> -->
<!--                     <th>当月售车提成（元）</th> -->
<!--                     <th>缴租提奖（元）</th> -->
<!--                     <th>半年提奖（元）</th> -->
<!--                     <th>当月销量（台）</th> -->
<!--                     <th>下载列表</th> -->
<!--                 </tr> -->
<!--             </thead> -->
<!--             <tbody> -->
                
<!--             </tbody> -->
<!--         </table> -->
        
<!--       <div style="width:100%; border: none;">   -->
<!-- 		   <div style="width:95%;float:left;" > -->
		   <table class="rl-page-table ui celled structured table span11" table-data-url="/lead/sale/summary/getStatListTable">
		    <thead>
		    <tr>
		      <th  data-field="id" data-formatter="xuhao"  width="10%">序号</th>
	          <th  data-field="salesman.name"  width="20%">销售人员</th>
	          <th data-field="bonusAmount" data-sortable="true" width="20%">总提成（元）</th>
	          <th data-field="sellBonus" data-sortable="true"  width="10%">当月售车提成（元）</th>
	          <th data-field="monthBonus" data-sortable="true"  width="10%">缴租提奖（元）</th>
	          <th data-field="halfYearBonus" data-sortable="true"  width="10%">半年提奖（元）</th>
	          <th data-field="salesNumber" data-sortable="true"  width="10%">当月销量（台）</th>
	          <!-- <th data-field="id" data-formatter="down" id="downth">下载列表</th> -->
	          <th data-colspan="8" data-field="id" data-formatter="down"  width="10%">下载</th>
	<!-- 	        <th data-field="id" data-align="right" data-sortable="true">Item ID</th> -->
		    </tr>
		    </thead>
		</table>
<!-- 		</div> -->
		
<!-- 		<div style="width:3%;float:right;border:solid 1px red;" > -->
<!-- 			下载 -->
<!-- 		</div> -->
<!-- 		<div class="clearfix"></div> -->
<!-- 	</div> -->

    </section>
    
    <!-- /.table -->
    <div id="barMain_s" style="height: 600px;width: 800px"></div>
 
    
    
    
    <link href="//cdn.bootcss.com/bootstrap-table/1.10.1/bootstrap-table.css" rel="stylesheet">
  	<script src="//cdn.bootcss.com/bootstrap-table/1.10.1/bootstrap-table.min.js"></script>
  	<script src="//cdn.bootcss.com/bootstrap-table/1.10.1/locale/bootstrap-table-zh-CN.min.js"></script>
  	
  	
  	<script src="http://echarts.baidu.com/echarts2/doc/example/timelineOption.js"></script>
  	
  	<script src="/static/stat/summary.js"></script>
    <script src="/static/stat/bootPaginator.js"></script>
<!--   	<script type="text/javascript" src="/static/bootstrap/2.3.1/bootstrap-paginator.js"></script> -->
</body>






</body>
</html>