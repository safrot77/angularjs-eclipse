/**
 *  Copyright (c) 2013-2014 Angelo ZERR.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *
 *  Contributors:
 *  Angelo Zerr <angelo.zerr@gmail.com> - initial API and implementation
 */
package org.eclipse.angularjs.core.utils;

import org.eclipse.angularjs.core.AngularProject;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Region;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMAttr;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

public class HyperlinkUtils {

	public static IRegion getNameRegion(IDOMAttr attr) {
		int regOffset = attr.getNameRegionStartOffset();
		int regLength = attr.getNameRegionText().length();
		/*String attValue = attr.getValueRegionText();
		if (StringUtils.isQuoted(attValue)) {
			regOffset++;
			regLength -= 2;
		}*/
		return new Region(regOffset, regLength);
	}

	public static IRegion getValueRegion(IDOMAttr attr) {
		int regOffset = attr.getValueRegionStartOffset();
		int regLength = attr.getValueRegionText().length();
		String attValue = attr.getValueRegionText();
		if (StringUtils.isQuoted(attValue)) {
			regOffset++;
			regLength -= 2;
		}
		return new Region(regOffset, regLength);
	}

	public static IRegion getElementRegion(IDOMElement element) {
		int endOffset;
		if (element.hasEndTag() && element.isClosed())
			endOffset = element.getStartEndOffset();
		else
			endOffset = element.getEndOffset();
		return new Region(element.getStartOffset(), endOffset
				- element.getStartOffset());
	}

	/**public static IRegion getHyperlinkRegion(Node node) {
		if (node != null)
			switch (node.getNodeType()) {
			case 3: // '\003'
			case 10: // '\n'
				IDOMNode docNode = (IDOMNode) node;
				return new Region(docNode.getStartOffset(),
						docNode.getEndOffset() - docNode.getStartOffset());

			case 1: // '\001'
				IDOMElement element = (IDOMElement) node;
				int endOffset;
				if (element.hasEndTag() && element.isClosed())
					endOffset = element.getStartEndOffset();
				else
					endOffset = element.getEndOffset();
				return new Region(element.getStartOffset(), endOffset
						- element.getStartOffset());

			case 2: // '\002'
				IDOMAttr att = (IDOMAttr) node;
				int regOffset = att.getValueRegionStartOffset();
				int regLength = att.getValueRegionText().length();
				String attValue = att.getValueRegionText();
				if (StringUtils.isQuoted(attValue)) {
					regOffset++;
					regLength -= 2;
				}
				return new Region(regOffset, regLength);
			}
		return null;
	}**/
	
	public static String getExpressionContent(final String expr) {
		String expression = expr;
		if (expression
				.startsWith(AngularProject.START_ANGULAR_EXPRESSION_TOKEN)) {
			expression = expression.substring(
					AngularProject.START_ANGULAR_EXPRESSION_TOKEN.length(),
					expression.length());
		}
		if (expression.endsWith(AngularProject.END_ANGULAR_EXPRESSION_TOKEN)) {
			expression = expression.substring(0, expression.length()
					- AngularProject.END_ANGULAR_EXPRESSION_TOKEN.length());
		}
		return expression;
	}
}
