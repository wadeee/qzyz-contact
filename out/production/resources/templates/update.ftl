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
		<div class="col-xs-8 col-xs-offset-2">
			<form action="/updateResult/" method="post">
				<div class="form-group">
					<label for="name" class="control-label sr-only">姓名</label>
					<div class="input-group">
						<div class="input-group-addon">姓名</div>
						<input class="form-control" id="name" name="name" value="${name}">
					</div>
				</div>
				<div class="form-group">
					<label for="phone" class="control-label sr-only">号码</label>
					<div class="input-group">
						<div class="input-group-addon">手机</div>
						<input class="form-control" id="phone" name="phone" value="${phone}" placeholder="13966666666">
					</div>
				</div>
				<div class="form-group">
					<label for="city" class="control-label sr-only">城市</label>
					<div class="input-group">
						<div class="input-group-addon">城市</div>
						<input class="form-control" id="city" name="city" value="${city}" placeholder="乌鲁木齐">
					</div>
				</div>
				<div class="col-xs-6">
					<a class="form-control btn btn-primary" href="/">返回</a>
				</div>
				<div class="col-xs-6">
					<input type="submit" class="form-control btn btn-primary" value="更新">
				</div>
			</form>
		</div>
	</div>
</div>
</body>
</html>