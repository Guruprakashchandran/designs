let eye = 0;
function changePasswordIcon() {
  let value = document.getElementById("password").value;
  console.log("guru");
  if (eye % 2 == 0) {
    document.querySelector(".passwordStyles").type = "text";
    // let value = document.getElementById("password").value;
    // let pass = document.getElementById("passwordDiv");
    document.querySelector("#eyeicon").style = "display:none";
    document.querySelector("#eyeicons").style = "display:block";
    // pass.innerHTML += '<i onclick="changePasswordIcon()" id = "eyeicon" class="fa fa-eye eye" aria-hidden="true"></i>';
    //  pass.innerHTML = '<span class ="mobileSpanStyle"><i class="fa fa-unlock-alt lockicon"></i></span><input id = "password" class = "passwordStyles" type = "Password" placeholder = "Password"><i onclick="changePasswordIcon()" id = "eyeicon" class="fa fa-eye eye" aria-hidden="true"></i>';
    // let pass = document.getElementById("passwor");
  } else {
    // document.querySelector("#eyeicon").style = "display:none";
    document.querySelector(".passwordStyles").type = "Password";
    // let value = document.getElementById("password").value;
    document.querySelector("#eyeicons").style = "display:none";
    document.querySelector("#eyeicon").style = "display:block";
    // let pass = document.getElementById("passwordDiv");
    // pass.innerHTML += '<i onclick="changePasswordIcon()" id = "eyeicons" class="fa fa-eye eye" aria-hidden="true"></i>';
    // pass.innerHTML = `<span class ="mobileSpanStyle"><i class="fa fa-unlock-alt lockicon"></i></span><input id = "password" class = "passwordStyles" value = ${value} type = "Password" placeholder = "Password"><i onclick="changePasswordIcon()" id = "eyeicon" class="fa fa-eye-slash" aria-hidden="true"></i>`;
  }
  eye++;
}
function checkPassword(event) {
  let button = document.getElementById("mobileNo");
  // if(button.value.length == 9){

  //     button.style=" border-bottom: 2px solid rgb(12, 252, 12);";
  // }
  if (button.value.length < 7) {
    console.log("reached");
    button.style = "border-bottom-color : red";
  } else {
    let code = document.getElementById("password");

    let key = event.which;
    console.log(event.which);
    if (key == 8 || key == 46) {
      button.style = " border-bottom: 2px solid black;";
    }
  }
  button.style = "border-bottom : 2px solid black";
}
function checkBackspace(event) {
  let code = document.getElementById("mobileNo");
  code.onkeydown = function () {
    let key = event.which;
    console.log(event.which);
    if (key == 8 || key == 46) {
      button.style = " border-bottom: 2px solid black;";
    }
  };
}
function checkNumber(event) {
  // let number = document.getElementById("mobileNo").value;
  let button = document.getElementById("mobileNo");
  // if(button.value.length == 9){

  //     button.style=" border-bottom: 2px solid rgb(12, 252, 12);";
  // }
  if (button.value.length > 10) {
    console.log("reached");
    button.style = "border-bottom-color : red";
  } else {
    let code = document.getElementById("mobileNo");

    let key = event.which;
    // console.log(event.which);
    if (key == 8 || key == 46) {
      console.log("border");
      button.style = " border-bottom: 2px solid black;";
    }
  }
}
function loginVerify(event) {
  let mobileNo = document.getElementById("mobileNo").value;
  let password = document.getElementById("password").value;
  if (mobileNo.length == 10) {
    if (password.length > 8) {
      // console.log(mobileNo);
      // console.log(password);
      let xml = new XMLHttpRequest();
      xml.onreadystatechange = function () {
        if (xml.readyState == 4 && xml.status == 200) {
          let res = JSON.parse(this.responseText);
          if (res.message == "Login Success") {
            //console.log("reaaakakak");
            window.location.href =
              "http://localhost:8085/ChatApp/ChatMainPage.html";
          } else {
            alert(res.message);
            event.preventDefault();
          }
        }
      };
      xml.open("POST", "http://localhost:8085/ChatApp/login", true);
      xml.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
      xml.send("mobileNumber=" + mobileNo + "&password=" + password);
    } else {
      event.preventDefault();
      alert("Minimum Password Length 8");
    }
  } else {
    event.preventDefault();
    alert("Wrong Mobile Number");
  }
}
