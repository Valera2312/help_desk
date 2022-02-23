function openDetail(id){
    document.location='/detail/' + id;
}
element = document.getElementById("priority");
if(element.innerText === "Высокий") {

    element.style.backgroundColor = "red";
}