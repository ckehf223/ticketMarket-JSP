document.addEventListener('DOMContentLoaded', () => {
	let seats = document.getElementsByClassName('seatCheckBox_lable');
	let limitAge = document.getElementById('limitAge').value;
	let userAge = document.getElementById('userAge').value;
	const loginId = document.getElementById('loginIdInput').value;
	Array.from(seats).forEach(seat => {
		seat.addEventListener('click', () => {
			if(loginId === null || loginId === ''){
				alert('로그인 후 이용가능합니다.')
			}else if (parseInt(limitAge) > parseInt(userAge)) {
				alert('관람연령' + limitAge + '세 이상부터 예매 가능합니다.\n 관람연령을 확인해 주세요!');
			} else {
				if (seat.style.backgroundColor === 'skyblue') {
					seat.style.backgroundColor = 'inherit';
				} else {
					seat.style.backgroundColor = 'skyblue';
				}
			}
		});
	});

	let resSeats = document.getElementsByClassName('seatCheckBox_res');
	Array.from(resSeats).forEach(seat => {
		seat.addEventListener('click', () => {
			alert('이미 예매된 좌석입니다');
		})
	})

	let seatBtn = document.getElementById('seatBoxButton');
	seatBtn.addEventListener('click', () => {
		let checkBoxValues = [];
		let count = 0;
		let seatInput = document.getElementsByClassName('seatCheckBox');
		Array.from(seatInput).forEach(seat => {
			if (seat.checked === true) {
				checkBoxValues.push(seat.value);
				count++;
			}
		})
		if (checkBoxValues.length > 0) {
			let query = {
				values: checkBoxValues,
				quantity: count,
				pf_id: document.getElementById('pf_id').value,
				pf_price: document.getElementById('pf_price').value,
				pf_no: document.getElementById('pf_no').value
			};
			var xhr = new XMLHttpRequest();
			xhr.open("POST", "/ticketMarket/reservation.do", true);
			xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			xhr.onreadystatechange = function() {
				if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
					alert('예매가 완료되었습니다.');
					window.location.href = '/ticketMarket/detailContent.do?no=' + encodeURIComponent(query.pf_no);
				} else {
					console.error('Error:', xhr.statusText);
				}
			};
			xhr.send("values=" + encodeURIComponent(query.values) +
				"&pf_id=" + encodeURIComponent(query.pf_id) +
				"&pf_price=" + encodeURIComponent(query.pf_price) +
				"&quantity=" + encodeURIComponent(query.quantity)
			);
		} else {
			alert('선택한 좌석이 없습니다.');
		}
	})
});