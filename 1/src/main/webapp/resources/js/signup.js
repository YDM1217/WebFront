const checkObj = {
    "inputId" : false,
    "inputPw" : false,
    "checkInputPw" : false,
    "inputNickname" : false
}

let inputId = document.getElementById("inputId");
let inputPw = document.getElementById("inputPw");
let checkInputPw = document.getElementById("checkInputPw");
let inputNickname = document.getElementById("inputNickname");


inputId.addEventListener("change", () => {

    const regExp = /^[a-z][a-zA-Z0-9]{5,13}$/;

    if (regExp.test(inputId.value)) {
        inputId.style.backgroundColor = "green";
        inputId.style.color = "white";
        checkObj.inputId = true;
    } else {
        inputId.style.backgroundColor = "red";
        inputId.style.color = "white";
        checkObj.inputId = false;
    }

});

checkInputPw.addEventListener("keyup", () => {

    if(inputPw.value.length == 0){
        checkInputPw.value = "";
        alert("비밀번호를 입력해주세요")
        inputPw.focus();
    }

});

checkInputPw.addEventListener("keyup", () => {
   
    if((inputPw.value == checkInputPw.value) && inputPw.value.length != 0){
        pwMsg.innerText = "비밀번호 일치";
        pwMsg.style.color = "green";
        pwMsg.classList.add("confirm");
        pwMsg.classList.remove("error");
        checkObj.inputPw = true;
        checkObj.checkInputPw = true;
    } else {
        pwMsg.innerText = "비밀번호 불일치";
        pwMsg.style.color = "red";
        pwMsg.classList.add("error");
        pwMsg.classList.remove("confirm");
        checkObj.inputPw = false;
        checkObj.checkInputPw = false;
    }

});


inputNickname.addEventListener("change", () => {

    const regExp = /^[가-힣]{2,5}$/;

    if(regExp.test(inputNickname.value)){
        nicknameMsg.innerText = "정상입력";
        nicknameMsg.style.color = "green";
        nicknameMsg.classList.add("confirm");
        nicknameMsg.classList.remove("error");
        checkObj.inputNickname = true;
    }else{
        nicknameMsg.innerText = "한글만 입력하세요";
        nicknameMsg.style.color = "red";
        nicknameMsg.classList.add("error");
        nicknameMsg.classList.remove("confirm");
        checkObj.inputNickname = false;
    }

});


function validate(){

    for (let key in checkObj) {
        if ( !checkObj[key] ){
            return false;
        }
    }

    return true;
}








