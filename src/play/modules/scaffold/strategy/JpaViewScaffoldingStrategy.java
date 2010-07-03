/**
 *
 * Copyright 2010, Lawrence McAlpin.
 *
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package play.modules.scaffold.strategy;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

import play.modules.scaffold.form.FormElement;
import play.modules.scaffold.form.FormElementType;
import play.modules.scaffold.utils.Classes;
import play.modules.scaffold.utils.Enums;

public class JpaViewScaffoldingStrategy extends DefaultViewScaffoldingStrategy
{

	@Override
	public FormElement render(Field field)
	{
		FormElement defaultValue = super.render(field);
		List<String> annotations = Classes.annotations(field);
		if (defaultValue.getType() == FormElementType.TEXT && annotations.contains("javax.persistence.Lob"))
		{
			return new FormElement(defaultValue, FormElementType.TEXTAREA);
		} if (annotations.contains("javax.persistence.Id"))
		{
			return new FormElement(defaultValue, FormElementType.HIDDEN);
		}
		return defaultValue;
	}
}
