/**
 * 
 */

//アコーディオンをクリックした時の動作
$('.title').on('click', function() {//タイトル要素をクリックしたら
	var findElm = $(this).next(".box");//直後のアコーディオンを行うエリアを取得し
	$(findElm).slideToggle();//アコーディオンの上下動作

	if ($(this).hasClass('close')) {//タイトル要素にクラス名closeがあれば
		$(this).removeClass('close');//クラス名を除去し
	} else {//それ以外は
		$(this).addClass('close');//クラス名closeを付与
	}
});

const save = async (year) => {
	const targetText = document.getElementById(year + '-textarea').value;
	try {
		const response = await fetch('http://localhost:8080/save/' + targetText + '/' + year);

		const text = await response.text();

		const element = document.createElement('li');
		element.textContent = text;

		document.getElementById(year + '-text').append(element);
		
		add(year);
	} catch (error) {
		console.error('Error:', error);
	}
}

const add = (year) => {
	const display = document.getElementById(year + '-inputarea').style.display;
	if (display == 'none') {
		document.getElementById(year + '-inputarea').style.display = 'block';
		document.getElementById(year + '-textarea').value = "";
		const button = document.getElementById(year + '-add');
		button.textContent = '×';
		button.classList.add('close-button');
	} else {
		document.getElementById(year + '-inputarea').style.display = 'none';
		const button = document.getElementById(year + '-add');
		button.textContent = '+';
		button.classList.remove('close-button');
	}


}