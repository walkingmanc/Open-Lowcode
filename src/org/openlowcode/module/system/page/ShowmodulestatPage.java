/********************************************************************************
 * Copyright (c) 2019-2020 [Open Lowcode SAS](https://openlowcode.com/)
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0 .
 *
 * SPDX-License-Identifier: EPL-2.0
 ********************************************************************************/

package org.openlowcode.module.system.page;

import org.openlowcode.module.system.action.LaunchsessioncleaningAction;
import org.openlowcode.module.system.action.ModuleusagesummaryAction;
import org.openlowcode.module.system.action.generated.AtgLaunchsearchmoduleusageAction;
import org.openlowcode.module.system.action.generated.AtgLaunchsearchusersessionAction;
import org.openlowcode.module.system.data.Modulereport;
import org.openlowcode.module.system.page.generated.AbsShowmodulestatPage;
import org.openlowcode.server.graphic.SPageNode;
import org.openlowcode.server.graphic.widget.SActionButton;
import org.openlowcode.server.graphic.widget.SComponentBand;
import org.openlowcode.server.graphic.widget.SObjectArray;
import org.openlowcode.server.graphic.widget.SPageText;

/**
 * Page to show the stats of the modules installed on this server
 * 
 * @author <a href="https://openlowcode.com/" rel="nofollow">Open Lowcode
 *         SAS</a>
 *
 */
public class ShowmodulestatPage
		extends
		AbsShowmodulestatPage {
	/**
	 * Creates the page to show modules installed on the server
	 * 
	 * @param parent parent module list of parent modules
	 */
	public ShowmodulestatPage(Modulereport[] modulereport) {
		super(modulereport);
	}

	@Override
	public String generateTitle(Modulereport[] modulereport) {
		return "Module Report for Server";
	}

	@Override
	protected SPageNode getContent() {
		SComponentBand mainband = new SComponentBand(SComponentBand.DIRECTION_DOWN, this);
		mainband.addElement(new SPageText("Server Usage", SPageText.TYPE_TITLE, this));
		mainband.addElement(
				new SPageText("Modules installed on the server are listed below.", SPageText.TYPE_NORMAL, this));
		SObjectArray<Modulereport> modulereport = new SObjectArray<Modulereport>("MODULEREPORT", this.getModulereport(),
				Modulereport.getDefinition(), this);

		mainband.addElement(modulereport);
		SComponentBand buttonband = new SComponentBand(SComponentBand.DIRECTION_RIGHT,this);
		mainband.addElement(buttonband);
		ModuleusagesummaryAction.ActionRef showmodulestat = ModuleusagesummaryAction.get().getActionRef();
		buttonband.addElement(new SActionButton("Show usage stats", showmodulestat, this));
		LaunchsessioncleaningAction.ActionRef sessioncleaning = LaunchsessioncleaningAction.get().getActionRef();
		buttonband.addElement(new SActionButton("Clean sessions",sessioncleaning,this));
		AtgLaunchsearchusersessionAction.ActionRef searchsessions = AtgLaunchsearchusersessionAction.get().getActionRef();
		buttonband.addElement(new SActionButton("Search sessions",searchsessions,this));
		AtgLaunchsearchmoduleusageAction.ActionRef searchmoduleusage = AtgLaunchsearchmoduleusageAction.get().getActionRef();
		buttonband.addElement(new SActionButton("Search module usage",searchmoduleusage,this));
		
		return mainband;
	}

}
