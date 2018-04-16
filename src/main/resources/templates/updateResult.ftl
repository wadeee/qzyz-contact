<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>高三一班通讯录更新</title>
	<link rel="stylesheet" href="/css/main.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	      integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>
<div class="container outside">
	<div class="row">
		<div class="col-xs-10 col-xs-offset-1">
			<div class="col-xs-6">
				<a class="form-control btn btn-primary" href="https://storage.googleapis.com/qzyz-contact.appspot.com/%E9%AB%98%E4%B8%89%E4%B8%80%E7%8F%AD%E9%80%9A%E8%AE%AF%E5%BD%95.xlsx">下载EXCEL</a>
			</div>
			<div class="col-xs-6">
				<a class="form-control btn btn-primary" href="/">返回</a>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<table class="table table-striped table-head">
				<tr>
					<th>姓名</th>
					<th>电话</th>
					<th>城市</th>
				</tr>
			    <#list messageBeans as messageBean>
				    <tr>
					    <td>${messageBean.name}</td>
					    <td>${messageBean.phone}</td>
					    <td>${messageBean.city}</td>
				    </tr>
			    </#list>
			</table>
		</div>
	</div>
</div>
</body>
</html>