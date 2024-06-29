document.addEventListener('DOMContentLoaded', () => {
	const addProductBtn = document.getElementById('addProductButton');
	addProductBtn.addEventListener('click', () => {
		let file = document.getElementById('productPoster').files[0];
		let formData = new FormData();
		formData.append('file', file);
		formData.append('productName', document.getElementById('productName').value);
		formData.append('productGenre', document.getElementById('productGenre').value);
		formData.append('productDate', document.getElementById('productDate').value);
		formData.append('productVenue', document.getElementById('productVenue').value);
		formData.append('productAge', document.getElementById('productAge').value);
		formData.append('productSeats', document.getElementById('productSeats').value);
		formData.append('productPrice', document.getElementById('productPrice').value);

		var xhr = new XMLHttpRequest();
		xhr.open('POST', '/ticketMarket/mg/productRegisterPro.do', true);
		xhr.onreadystatechange = function() {
			if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
				alert('공연이 등록되었습니다.')
				window.location.href = '/ticketMarket/mg/productList.do';
			} else if (xhr.readyState === 4) {
				console.error('Error:', xhr.statusText);
			}
		};
		xhr.onerror = function() {
			console.error('Request failed');
		};
		xhr.send(formData);
	})


	const resetProductBtn = document.getElementById('resetProductButton');
	resetProductBtn.addEventListener('click', () => {
		window.location.href = '/ticketMarket/mg/productList.do';
	})

})