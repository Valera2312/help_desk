
$(document).ready(function(){
    PopUpHide();
    popupAddPriorityHide();
    popupAddExecutorHide();
});
function PopUpShow(){
    $("#popup1").show();
}
function PopUpHide(){
    $("#popup1").hide();
}


function popupAddPriorityShow(){
    $("#popupAddPriority").show();
}
function popupAddPriorityHide(){
    $("#popupAddPriority").hide();
}

function popupAddExecutorShow(){
    $("#popupAddExecutor").show();
}
function popupAddExecutorHide(){
    $("#popupAddExecutor").hide();
}