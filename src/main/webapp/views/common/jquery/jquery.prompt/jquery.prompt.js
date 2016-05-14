(function (T) {
    T.extend({
        prompt: function (options) {
            var _type = options._type || 'MsgDialog';
            var _url = options._url || "";
            var _title = options._title || "信息弹出框";
            var _dialogH = options._dialogH || 490;
            var _dialogW = options._dialogW || 600;
            var _msg = options._msg || "";
            var _data = options._data || {};
            var _countdown = options._countdown || false;
            var _times = options._times || 3000;
            var _msgicon = options._msgicon || "success";
            var _callback = options._callbackfn || function () { };
            var _closeDialog = options._closeDialog || false;
            /*options = {
                _type: 'Dialog', // MsgDialog 包括{success,error,information,warning} 场景为各种操作后的提示框, Dialog 场景为新增时弹出的框可以用URL加载页面或直接Html传入渲染页面,ConfirmDialog 有确认识取消，场景为删除前提示是否确认要删除,
                _url: "http://xx.html", //使用场景要与【Dialog】 结合 
                _title: '信息弹出框', //使用场景要与【Dialog,ConfirmDialog】 结合 
                _dialogH:450,//使用场景要与【Dialog】结合,弹出框高度，有默认值
                _dialogW:500,//使用场景要与【Dialog】结合,弹出框宽度，有默认值
                _msg: "",//使用场景要与【MsgDialog】结合
                _data:{} //使用场景要与【Dialog】结合，参数
                _countdown:true, ////使用场景要与【MsgDialog】结合 是否开启自动关闭，可与_times一起使用
                _times:1000 //1分钟后关闭
                _msgicon: "success",//图片Icon 图片样式名使用场景要与【MsgDialog】结合
                _fn: function () {
                    //使用场景为【Dialog，ConfirmDialog】结合 _callback
                },
                _closeDialog:false ////使用场景为【Dialog，ConfirmDialog】,是否关闭父框体
            }*/
            //var _NotesDialog;  //记录Dialog element ，结合 _closeDialog 使用
            var _Html = [];
            var _Backdrop = "";
            var _MsgDialog = ['<div class="prompt"><i class="', _msgicon, '"></i><span>', _msg, '</span></div>'];
            var _Dialog = ['<div id="modalDialog" class="modal fade in" >' +
                '<div class="modal-dialog" style="width: ' + _dialogW + 'px ; margin-top:150px ">' +
                    '<div class="modal-content">' +
                        '<div class="modal-header m-common-bg">' +
                            '<button type="button" class="close m-common-bg" data-dismiss="modal" aria-hidden="true"></button>' +
                            '<div class="modal-title"><span class="glyphicon glyphicon-info-sign">' + _title + '</span></div></div>' +
                        '<div class="modal-body" style="text-align: center;"></div>' +
                        '<div class="modal-footer"><input type="hidden" id="parm_val" /></div>' +
                    '</div>' +
                '</div>' +
            '</div>'];

            var _ConfirmDialog = ['<div id="confirmDialog" class="modal fade in" >' +
                    '<div class="modal-dialog" style="width: ' + _dialogW + 'px ; margin-top:150px ">' +
                        '<div class="modal-content">' +
                            '<div class="modal-header m-common-bg">' +
                                '<button type="button" class="close m-common-bg" data-dismiss="modal" aria-hidden="true"></button>' +
                                '<div class="modal-title"><span class="glyphicon glyphicon-info-sign">' + _title + '</span></div></div>' +
                                '<div class="modal-body" style="text-align: center;">' +
                                    '<p class="dialogcontent">', _msg, '</p>' +
                                    '<div class="butt">' +
                                        '<button class="but but_ok">确定</button><button class="but cancel but_close">取消</button>' +
                                    '</div>' +
                                '</div>' +
                            '<div class="modal-footer"><input type="hidden" id="parm_val" /></div>' +
                        '</div>' +
                    '</div>' +
                '</div>'];

            window.onmousewheel=function(){return false ;};  
            if (_type === "Dialog") {
                _Backdrop = '<div class="prompt-backdrop dialog-backdrop"></div>';
                _Html = _Dialog;
            } else if (_type === "MsgDialog") {
                _Backdrop = '<div class="prompt-backdrop msgdialog-backdrop"></div>';
                _Html = _MsgDialog;
            } else if (_type === "ConfirmDialog") {
                _Backdrop = '<div class="prompt-backdrop dialog-backdrop"></div>';
                _Html = _ConfirmDialog;
            }

            (function (element) {
                if (!!element) {
                    element = $(element.join('')).appendTo('body');
                    // element.css({
                    // 'marginLeft': -((element.width() + 25 * 2 /*padding*/ + 2 /*border*/ ) /2),
                    // 'marginTop': -((element.height() + 14 * 2 /*padding*/ + 2 /*border*/) /2 )
                    // });
                    if (_type == "MsgDialog") {
                        //重新设计提示框位置
                        /*T(window).bind('resize scroll', function () {
                            element.css({
                                'top': ($(window).height() - element.height()) / 2 + $(window).scrollTop(),
                                'left': ($(window).width() - element.width()) / 2
                            });
                        });*/
                        element.css({
                            'top': ($(window).height() - element.height()) / 2 ,
                            'left': ($(window).width() - element.width()) / 2
                        });
                    } 
                    element.fadeIn();
                    element.after(_Backdrop);
                    // element.after('<div class="prompt-backdrop"></div>');
                    //【Dialog】 弹出框 操作代码段
                    if (_type === "Dialog") {
                        var $elDialogdialog = $(".dialog-backdrop");
                        var randomNum = Math.floor(Math.random() * (2014 + 1));
                        //console.log(_url.lastIndexOf('?') == 0);
                        /*if (_url.lastIndexOf('?') == 0) {
                            _url += ("?vNum=" + randomNum);
                        } else {
                            _url += ("&vNum=" + randomNum);
                        }*/
                        var _search = _url.substring(_url.lastIndexOf('?'), _url.length);
                        $("#parm_val").val(_search);
                        element.find(".modal-body").load(_url, function () {
                            element.find(".but-close").unbind("click");
                            element.find(".but-close").bind("click", function () {
                            	window.onmousewheel=function(){return true ;};  
                                element.remove();
                                $elDialogdialog.remove();
                                !!_callback && _callback();
                            });

                        });
                        //$el_Dialog.show();
                        $("#modalDialog").find(".close").click(function () { 
                            window.onmousewheel=function(){return true ;};  
                            element.remove();
                            $elDialogdialog.remove();
                        });

                    }
                        //END 【Dialog】 弹出框 操作代码段
                        //【MsgDialog】 信息提示框 操作代码段 
                    else if (_type === "MsgDialog") {
                        var $elMsgdialog = $(".msgdialog-backdrop");
                        if (_countdown) {
                            setTimeout(function () {
                                if (!!element) {
                                    element.fadeOut(function () {
                                        if (_closeDialog) {
                                            $("button.close").click();
                                            var $elDialogdialog = $(".dialog-backdrop");
                                            $elDialogdialog.remove();
                                        }
                                        window.onmousewheel=function(){return true ;};  
                                        element.remove();
                                        $elMsgdialog.remove();
                                        !!_callback && _callback();
                                    });
                                }
                            }, _times);
                        }
                        $elMsgdialog.click(function () {
                        	window.onmousewheel=function(){return true ;};  
                            element.remove();
                            $elMsgdialog.remove();
                            !!_callback && _callback();
                        });
                    }
                        //END 【MsgDialog】 信息提示框 操作代码段 
                    else if (_type === "ConfirmDialog") {
                        var $elConfirmDialog = $(".dialog-backdrop");
                        $("#confirmDialog").find(".but_ok").click(function () {
                        	window.onmousewheel=function(){return true ;};  
                            element.remove();
                            $elConfirmDialog.remove();
                            !!_callback && _callback(true);
                        });
                        $("#confirmDialog").find(".close").click(function () {
                        	window.onmousewheel=function(){return true ;};  
                            element.remove();
                            $elConfirmDialog.remove();
                        });
                        
                        $("#confirmDialog").find(".but_close").click(function () {
                        	window.onmousewheel=function(){return true ;};  
                            element.remove();
                            $elConfirmDialog.remove();
                            !!_callback && _callback(false);
                        });
                    }
                }
            })(_Html);
        } 
    });
})(jQuery);