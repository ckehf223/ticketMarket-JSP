document.addEventListener('DOMContentLoaded', () => {
	let modifyBtn = document.getElementById('modifyButton');
	modifyBtn.addEventListener('click', () => {
		window.location.href = "/ticketMarket/modifyForm.do";
	});

	let deleteBtn = document.getElementById('deleteButton');
	deleteBtn.addEventListener('click', () => {
		let yesNo = prompt("정말삭제하시겠습니까?  YES/NO 입력");
		if (yesNo.toLocaleUpperCase() === 'YES') {
			var xhr = new XMLHttpRequest();
			xhr.open("POST", "/ticketMarket/deletePro.do", true);
			xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			xhr.onreadystatechange = function() {
				if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
					var data = xhr.responseText;
					var str1 = '<p id="ck">';
					var loc = data.indexOf(str1);
					var len = str1.length;
					var check = data.substr(loc + len, 1);
					if (check === '1') {
						alert('정상적으로 삭제되었습니다.');
						window.location.href = '/ticketMarket/index.do';
					} else {
						alert('삭제 실패 error!!!');
					}
				} else {
					console.error('Error:', xhr.statusText);
				}
			};
			xhr.send();
		}
	})
	
	//예매내역 전체 취소
	let deleteAllBtn = document.getElementById('cencelAllButton');
	deleteAllBtn.addEventListener('click',()=>{
		let yesNo = prompt("정말삭제하시겠습니까?  YES/NO 입력");
		if (yesNo.toLocaleUpperCase() === 'YES') {
			var xhr = new XMLHttpRequest();
			xhr.open("POST", "/ticketMarket/deleteAllReservation.do", true);
			xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			xhr.onreadystatechange = function() {
				if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
						alert('정상적으로 삭제되었습니다.');
						window.location.href = '/ticketMarket/myPage.do';
				} else {
					console.error('Error:', xhr.statusText);
				}
			};
			xhr.send();
		}
	});
	
	//결제하기
	let paymentBtn = document.getElementById('paymentButton');
	paymentBtn.addEventListener('click',()=>{
		window.location.href='/ticketMarket/paymentCart.do';
	})
});

function cancelTicket(cartNo){
	let yesNo = prompt('예매하신 항목을 취소하시겠습니까? YES/NO');
	if(yesNo.toUpperCase() === 'YES'){
			var xhr = new XMLHttpRequest();
			xhr.open("POST", "/ticketMarket/deleteReservation.do", true);
			xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			xhr.onreadystatechange = function() {
				if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
						alert('예매하신 공연이 취소되었습니다.');
						window.location.href='/ticketMarket/myPage.do';
				} else {
					console.error('Error:', xhr.statusText);
				}
			};
			xhr.send("orderNum=" + encodeURIComponent(cartNo));
	}
}

