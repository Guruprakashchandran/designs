function checkbox(button){
    
    if(button.checked){

        document.querySelector("label[for = 'next']").innerHTML = "<i class='fa fa-angle-double-left leftarrow'></i>Previous<i class='fa fa-angle-double-right rightarrow'></i>";
        document.querySelector(".RegisterContainer1").style = "display : none";
        document.querySelector(".RegisterContainer2").style = "display : block";
        document.querySelector(".submitButtonStyle").style = "display : block";
        document.querySelector(".leftarrow").style = "display : inline-block";
        document.querySelector(".rightarrow").style = "display : none";
    }
    else{

        document.querySelector("label[for = 'next']").innerHTML = "<i class='fa fa-angle-double-left leftarrow'></i>Next<i class='fa fa-angle-double-right rightarrow'></i>";
        document.querySelector(".RegisterContainer2").style = "display : none";
        document.querySelector(".RegisterContainer1").style = "display : block";
        document.querySelector(".submitButtonStyle").style = "display : none";
        document.querySelector(".leftarrow").style = "display : none";
        document.querySelector(".rightarrow").style = "display : inline-block";
    }
}
function ValidateDetails(){

    let name = document.getElementById("nickname").value;
    let mobileno = document.getElementById("mobileno").value;
    let emailid = document.getElementById("emailid").value;
    let dateofbirth = document.getElementById("dateofbirth").value;
    let password = document.getElementById("pass").value;
    let confirmpassword = document.getElementById("conpass").value;
    document.write(name);
}
function checkNumber(button){

    let number = button.value;
    // console.log(number.length);
    if(number.length > 9){
        console.log("reached");
        button.style="border-bottom-color : red";
    }
    else {

        button.style=" border-bottom: 2px solid black;";
    }
    if(number.length == 9){

        button.style=" border-bottom: 2px solid green;";
    }
}