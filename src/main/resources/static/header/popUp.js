
$(document).ready(function(){
    PopUpHide();
    popupAddPriorityHide();
    popupAddExecutorHide();

    let priorityTd = document.getElementById("priority");
    if (priorityTd.innerText === "Высокий") {

        priorityTd.style.backgroundColor = "red";

    }else if (priorityTd.innerText === "Средний") {
        priorityTd.style.backgroundColor = "orange";
    }else {
        priorityTd.style.backgroundColor = "yellow";
    }
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