function writeComment(boardId, parentId = 0, depth = 0, sequence = 0){
	const content = document.querySelector("textarea[name='comment-content']").value
	
	fetch('/api/comment/' + boardId, {
		method: 'POST',
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify({
			content: content,
			parentId: parentId,
			depth: depth,
			sequence: sequence
		})
	}).then(response => {
		if(response.ok){
			console.log(response);
			location.reload();
		}else{
			alert('Login');
		}
	}).catch(error => console.error(error.message));
}

function modifyComment(commentId){
	
	const content = document.querySelector("#comment-content-" + commentId).value
	console.log(content);
	fetch('/api/comment/' + commentId, {
		method: 'PATCH',
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify({
			content: content,
		})
	}).then(response => {
		if(response.ok){
			console.log(response);
			location.reload();
		}else{
			alert('Login');
		}
	}).catch(error => console.error(error.message));
}

function deleteComment(commentId){
	fetch('/api/comment/'+commentId, {
		method: 'DELETE',
	}).then(response => {
		if(response.ok){
			console.log(response);
			location.reload();
		}else{
			alert('권한 없음');
		}
	}).catch(error => console.error(error.message));
}



function mode_modify(commentId){
	if (document.querySelector('#comment-body-'+commentId).style.display === 'none'){
		modifyComment(commentId);
	} else{
		$('#comment-body-'+commentId).hide();
		$('#comment-modify-'+commentId).show();
		document.querySelector('#comment-content-'+commentId).value = document.querySelector('#content-'+commentId).innerText;	
	}
	
}