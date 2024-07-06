document.addEventListener('DOMContentLoaded', () => {
	let checkIdBtn = document.getElementById('chekcIdButton');
	checkIdBtn.addEventListener('click', () => {
		let checkId = document.getElementById('userId').value;
		let message = document.getElementById('userIdMessage');
		
		if (checkId) {
			let query = { id: checkId };
			
			let xhr = new XMLHttpRequest();
			
			xhr.open("POST", "/ticketMarket/confirmId.do", true);
			xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			
			xhr.onreadystatechange = function() {
				if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
					var data = xhr.responseText;
					var str1 = '<p id="ck">';
					var loc = data.indexOf(str1);
					var len = str1.length;
					var check = data.substr(loc + len, 1);
					if (check === '1') {
						message.innerHTML = '사용할 수 없는 아이디';
						message.style.color = 'red';
					} else {
						message.innerHTML = '사용할 수 있는 아이디';
						message.style.color = 'green';
					}
				} else {
					console.error('Error:', xhr.statusText);
				}
			};
			xhr.send("userId=" + encodeURIComponent(query.id));
		} else {
			checkId.focus();
		}
	});
	
	
	
	
	
	
	
	let cancelBtn = document.getElementById('reset-button');
	cancelBtn.addEventListener('click', () => {
		window.location.href = '/ticketMarket/index.do';
	});
	

	//생년월일 select 

	//selectBox yearList
	let yearList = document.getElementById('year-list');
	let isYearOption = false;

	yearList.addEventListener('focus', () => {
		if (!isYearOption) {
			isYearOption = true;
			for (let i = 2023; i >= 1920; i--) {
				let yearOption = document.createElement("option");
				yearOption.setAttribute("value", i);
				yearOption.innerText = i + "년";
				yearList.appendChild(yearOption);
			}
		}
	})

	//selectBox monthList
	let monthList = document.getElementById('month-list');
	let isMonthOption = false;

	monthList.addEventListener("focus", () => {
		if (!isMonthOption) {
			isMonthOption = true;

			for (let i = 1; i <= 12; i++) {
				let monthOption = document.createElement("option");
				if (i < 10) {
					monthOption.setAttribute("value", "0" + i);
					monthOption.innerText = `0${i}월`;
				} else {
					monthOption.setAttribute("value", i);
					monthOption.innerText = `${i}월`;
				}
				monthList.appendChild(monthOption);
			}
		}
	})

	//selectBox dayList
	let dayList = document.getElementById('day-list');
	let isDayOption = false;

	dayList.addEventListener("focus", () => {
		if (!isDayOption) {
			isDayOption = true;

			for (let i = 1; i <= 31; i++) {
				let dayOption = document.createElement("option");
				if (i < 10) {
					dayOption.setAttribute("value", "0" + i);
					dayOption.innerText = `0${i}일`;
				} else {
					dayOption.setAttribute("value", i)
					dayOption.innerText = i + "일";
				}
				dayList.appendChild(dayOption);
			}
		}
	})

	//userBirth input select value 넣어주기
	let eventYear = document.querySelector("#userYear");
	let eventYearList = document.querySelector("#year-list");
	let eventMonth = document.querySelector("#userMonth");
	let eventMonthList = document.querySelector("#month-list");
	let eventDay = document.querySelector("#userDay");
	let eventDayList = document.querySelector("#day-list");

	eventYearList.addEventListener("change", event => {
		eventYear.value = event.target.value;
		eventYear.disabled = true;
	})
	eventMonthList.addEventListener("change", event => {
		eventMonth.value = event.target.value;
		eventMonth.disabled = true;
	})
	eventDayList.addEventListener("change", event => {
		eventDay.value = event.target.value;
		eventDay.disabled = true;
	})

	//domain 
	let domainChange = document.querySelector("#domainChange");
	let domain = document.querySelector("#domain");

	domainChange.addEventListener("change", event => {
		if (event.target.value !== "direct") {
			domain.value = event.target.value;
			domain.disabled = true;
		} else {
			domain.value = "";
			domain.disabled = false;
		}
	})

});


//아이디
function userIdCheck() {
	const userId = document.querySelector("#userId");
	const userIdMessage = document.querySelector("#userIdMessage");
	if (userId.value === "") {
		userIdMessage.innerHTML = `*필수입력항목`;
		userIdMessage.style.color = `red`;
		return false;
	} else {
		return true;
	}
}

//패스워드
function userPwCheck() {
	const userPw = document.querySelector("#userPw");
	const userPwMessage = document.querySelector("#userPwMessage");
	let passwordExp = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@$%^&*]).{8,16}$/;

	if (userPw.value === "") {
		userPwMessage.innerHTML = `*필수입력항목`;
		userPwMessage.style.color = `red`;
		return false;
	} else if (!userPw.value.match(passwordExp)) {
		userPwMessage.innerHTML = `사용불가`;
		userPwMessage.style.color = `red`;
		return false;
	} else {
		userPwMessage.innerHTML = `사용가능`;
		userPwMessage.style.color = `green`;
		return true;
	}

}
//패스워드 확인
function userPwokCheck() {
	const userPw = document.querySelector("#userPw");
	const userPwok = document.querySelector("#userPwok");
	const userPwokMessage = document.querySelector("#userPwokMessage");

	if (userPwok.value === "") {
		userPwokMessage.innerHTML = `*필수입력항목`;
		userPwokMessage.style.color = `red`;
		return false;
	} else if (!(userPwok.value === userPw.value)) {
		userPwokMessage.innerHTML = `일치하지 않음`;
		userPwokMessage.style.color = `red`;
		return false;
	} else {
		userPwokMessage.innerHTML = `일치함`;
		userPwokMessage.style.color = `green`;
		return true;
	}

}
//이름
function userNameCheck() {
	const userName = document.querySelector("#userName");
	const nameMessage = document.querySelector("#nameMessage");
	let nameExp = /^[가-힣]{2,4}$/;
	let color = '';
	if (userName.value === "") {
		nameMessage.innerHTML = `*필수입력항목`;
		nameMessage.style.color = `red`;
		color = 'red';
		return false;
	} else if (!(userName.value.match(nameExp))) {
		nameMessage.innerHTML = `2~4자 한글만 입력가능`;
		nameMessage.style.color = `red`;
		color = 'red';
		return false;
	} else if (color === 'red') {
		return false;
	} else {
		nameMessage.innerHTML = ``;
		color = '';
		return true;
	}
}
//생년월일
function userBirthCheck() {
	const userYear = document.querySelector("#userYear");
	const userMonth = document.querySelector("#userMonth");
	const userDay = document.querySelector("#userDay");
	const userBirthMessage = document.querySelector("#userBirthMessage");

	let birthExp = /^[0-9]*$/;
	if (userYear.value === "" || userMonth.value === "" || userDay.value === "") {
		userBirthMessage.innerHTML = `*필수입력항목`;
		userBirthMessage.style.color = `red`;
		return false;
	} else if (!userYear.value.match(birthExp) || !userMonth.value.match(birthExp) || !userDay.value.match(birthExp)) {
		userBirthMessage.innerHTML = `*숫자만 입력`;
		userBirthMessage.style.color = `red`;
		return false;
	} else {
		userBirthMessage.innerHTML = "";
		return true;
	}
}




//이메일
function userEmailCheck() {
	const userEmail = document.querySelector("#userEmail");
	const domain = document.querySelector("#domain");
	const domainMessage = document.querySelector("#domainMessage");

	let emailExp = /^[a-zA-Z0-9._-]{1,12}$/;
	let domainExp = /^[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;

	if (userEmail.value === "" || domain.value === "") {
		domainMessage.innerHTML = `*필수입력항목`;
		domainMessage.style.color = `red`;
		return false;
	} else if (!userEmail.value.match(emailExp) || !domain.value.match(domainExp)) {
		domainMessage.innerHTML = `형식에 맞게 입력`;
		domainMessage.style.color = `red`;
		return false;
	} else {
		domainMessage.innerHTML = "";
		return true;
	}
}

function sample6_execDaumPostcode() {
	new daum.Postcode({
		oncomplete: function(data) {
			//팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
			//각 주소의 노출 규칙에 따라 주소를 조합한다.
			//내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 한다

			var addr = '';          //주소변수
			var extraAddr = '';     //참고항목 변수

			//사용자가 선택한 주소 타입에 따라 해당 주소값을 가져온다.
			//사용자가 도로명 주소를 선택했을 경우


			//사용자가 선택한 주소가 도로명 탑입일때 참고항목을 조합한다.
			if (data.userSelectedType === 'R') {
				//법정동명이 있을 경우 추가한다. (법정리는 제와)
				//법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
				addr = data.roadAddress;
				if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
					extraAddr += data.bname;
				}

				//건물명이 있고, 공동주택일 경우 추가한다.
				if (data.buildingName !== '' && data.apartment === 'Y') {

					extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
				}
				//표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
				if (extraAddr !== '') {
					addr += '(' + extraAddr + ')';
				}
				//조합된 참고항목을 해당 필드에 넣는다.
				// document.getElementById("sample6_extraAddress").value = extraAddr;


			} else {
				addr = data.jibunAddress;
			}
			// else {
			//   document.getElementById("sample6_extraAddress").value = '';
			// }

			//우편번호와 주소 정보를 해당 필드에 넣는다.
			document.getElementById('userPostal').value = data.zonecode;
			document.getElementById('userAddress').value = addr;
			//커서를 상세주소 필드로 이동한다.
			document.getElementById("userDetailAddress").focus();
		}
	}).open();
}



//우편번호
function userPostalCheck() {
	const userPostal = document.querySelector("#userPostal");
	const postalMessage = document.querySelector("#postalMessage");
	if (userPostal.value === "") {
		postalMessage.innerHTML = `*필수입력항목`;
		postalMessage.style.color = `red`;
		return false;
	} else {
		postalMessage.innerHTML = ``;
		return true;
	}
}
//주소
function userAddressCheck() {
	const userAddress = document.querySelector("#userAddress");
	const addressMessage = document.querySelector("#addressMessage");
	if (userAddress.value === "") {
		addressMessage.innerHTML = `*필수입력항목`;
		addressMessage.style.color = `red`;
		return false;
	} else {
		addressMessage.innerHTML = ``;
		return true;
	}
}
//전화번호
function userPhoneCheck() {
	const userPhone = document.querySelector("#userPhone");
	const PhoneMessage = document.querySelector("#PhoneMessage");

	let phoneExp = /^(01[016789]{1})-?[0-9]{3,4}-?[0-9]{4}$/;
	if (userPhone.value === "") {
		PhoneMessage.innerHTML = `*필수입력항목`;
		PhoneMessage.style.color = `red`;
		return false;
	} else if (!userPhone.value.match(phoneExp)) {
		PhoneMessage.innerHTML = `유효하지 않음`;
		PhoneMessage.style.color = `red`;
		return false;
	} else {
		PhoneMessage.innerHTML = ``;
		return true;
	}
}

//모든 함수가 true 인지 확인
function allCheck() {
	if (userIdCheck() && userPwCheck() && userPwokCheck() && userPostalCheck()
		&& userEmailCheck() && userNameCheck() && userBirthCheck() && userAddressCheck() && userPhoneCheck()) {
		let query = {
			id: document.getElementById('userId').value,
			passwd: document.getElementById('userPw').value,
			name: document.getElementById('userName').value,
			email: document.getElementById('userEmail').value,
			domain: document.getElementById('domainChange').value,
			year: document.getElementById('year-list').value,
			month: document.getElementById('month-list').value,
			day: document.getElementById('day-list').value,
			address1: document.getElementById('userAddress').value,
			address2: document.getElementById('userDetailAddress').value,
			address3: document.getElementById('userPostal').value,
			tel: document.getElementById('userPhone').value
		};
		let xhr = new XMLHttpRequest();
		xhr.open("POST", "/ticketMarket/registerPro.do", true);
		xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		xhr.onreadystatechange = () => {
			if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
				alert("정상적으로 회원가입이되었습니다.");
				window.location.href = "/ticketMarket/index.do";
			} else {
				console.error('Error:', xhr.statusText);
			}
		};
		xhr.send("userId=" + encodeURIComponent(query.id) +
			"&userPw=" + encodeURIComponent(query.passwd) +
			"&userName=" + encodeURIComponent(query.name) +
			"&userYear=" + encodeURIComponent(query.year) +
			"&userEmail=" + encodeURIComponent(query.email+"@"+query.domain) +
			"&userBirth=" + encodeURIComponent(query.year + query.month + query.day) +
			"&userAddress=" + encodeURIComponent(query.address1 +"/"+ query.address2 + "/(" + query.address3 + ")") +
			"&userPhone=" + encodeURIComponent(query.tel)
		);
	} else {
		alert(`제대로 입력하지 않은 항목이 존재합니다. \n다시 확인해 주세요!`);
		return;
	}
}
