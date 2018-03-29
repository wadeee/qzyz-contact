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
			<form action="/update/">
				<div class="form-group">
					<label for="name" class="control-label sr-only">君の名は</label>
					<div class="input-group">
						<div class="input-group-addon">君の名は</div>
						<select id="name" name="name" class="form-control">
							<#list nameList as name>
								<option>
									${name}
								</option>
							</#list>
						</select>
					</div>
				</div>
				<div class="col-xs-6">
					<input type="submit" class="form-control btn btn-primary" value="确认">
				</div>
				<div class="col-xs-6">
					<a class="form-control btn btn-primary" href="/updateResult/">下载</a>
				</div>
			</form>
		</div>
	</div>
</div>
</body>
</html>