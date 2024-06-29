document.addEventListener('DOMContentLoaded', () => {
	const commentAddButton = document.getElementById('commentAddButton');
	commentAddButton.addEventListener('click', () => {
		let commentText = document.getElementById('commentWriteContent').value;
		if (commentText === null || commentText === '') {
			alert('댓글내용을 입력해주세요');
		} else {
			let query = {
				content: commentText,
				writer: document.getElementById('commentWriter').value,
				num: document.getElementById('notice_num').value
			};
			var xhr = new XMLHttpRequest();
			xhr.open('POST', '/ticketMarket/commentAddPro.do', true);
			xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
			xhr.onreadystatechange = () => {
				if (xhr.readyState === 4 && xhr.status === 200) {
					var comment = JSON.parse(xhr.responseText);
					const commentsContainer = document.getElementById('comments');
					const commentElement = createCommentElement(comment);
					commentsContainer.appendChild(commentElement);
					document.getElementById('commentWriteContent').value = '';
				} else if (xhr.readyState === 4) {
					console.error('Error adding comment:', xhr.statusText);
				}

			}
				xhr.send("writer=" + encodeURIComponent(query.writer)
				+"&content="+ encodeURIComponent(query.content)
				+"&num="+ encodeURIComponent(query.num));
		}
	})
	
	const modifyBtn = document.getElementById('modifyButton');
	modifyBtn.addEventListener('click',()=>{
		let num = document.getElementById('notice_num').value;
		window.location.href=`/ticketMarket/mg/noticeModify.do?num=${num}`;
	})
	
	const deleteBtn = document.getElementById('noticeDeleteButton');
	deleteBtn.addEventListener('click',()=>{
		let yesNo = prompt('정말 삭제하시겠습니까? YES/NO');
		if(yesNo.toUpperCase() === 'YES'){
			let num = document.getElementById('notice_num').value;
			var xhr = new XMLHttpRequest();
			xhr.open('POST', '/ticketMarket/mg/deleteNoticePro.do', true);
			xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
			xhr.onreadystatechange = () => {
				if (xhr.readyState === 4 && xhr.status === 200) {
					alert('삭제가 완료되었습니다.')
						window.location.href='/ticketMarket/mg/noticeList.do';
				} else if (xhr.readyState === 4) {
					console.error('Error adding comment:', xhr.statusText);
				}

			}
				xhr.send("num=" + encodeURIComponent(num));	
		}
	})
	
});


function createCommentElement(comment) {
	const div = document.createElement('div');
	div.className = 'comments_Area';
	div.id = `comment-${comment.no}`;
	div.innerHTML = `
                <div class="titleArea">
                    <span class="comment_writerId">${comment.id}</span>
                </div>
                <div>
                    <span class="comment_content">${comment.content}</span>
                    <br>
                    <span class="comment_writeDate">${comment.regdate}</span>
                    <button class="commentAreaButton" onclick="deleteComment(${comment.no})">삭제</button>
                </div>`;
	return div;
}


function deleteComment(commentId) {
	var xhr = new XMLHttpRequest();
	xhr.open('POST', `/ticketMarket/commentDelPro.do?`, true);
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4 && xhr.status === 200) {
			const commentElement = document.getElementById(`comment-${commentId}`);
			if (commentElement) {
				commentElement.remove();
			}
		} else if (xhr.readyState === 4) {
			console.error('Error deleting comment:', xhr.statusText);
		}
	};
	xhr.send("commentNo=" + encodeURIComponent(commentId));
}

