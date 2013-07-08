/*
 * Copyright (c) 2012, intarsys consulting GmbH
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * - Redistributions of source code must retain the above copyright notice,
 *   this list of conditions and the following disclaimer.
 *
 * - Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * - Neither the name of intarsys nor the names of its contributors may be used
 *   to endorse or promote products derived from this software without specific
 *   prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package de.intarsys.tools.concurrent;

import java.util.ArrayList;
import java.util.List;

/**
 * experimental
 */
public class TaskSequence<R> extends AbstractFutureTask<R> {

	private List<Runnable> tasks = new ArrayList<Runnable>();

	private String labelPrefix;

	private Runnable currentTask = null;

	public TaskSequence(String labelPrefix) {
		super();
		// setWorkTotal(0);
		this.labelPrefix = labelPrefix;
	}

	public void addTask(Runnable task, int percent) {
		tasks.add(new TaskStep(this, task, percent));
		// setWorkTotal(getWorkTotal() + percent);
	}

	@Override
	protected R compute() throws Exception {
		try {
			R result = null;
			for (Runnable task : tasks) {
				synchronized (this) {
					currentTask = task;
				}
				task.run();
				if (isCancelled()) {
					return null;
				}
			}
			return result;
		} finally {
			synchronized (this) {
				currentTask = null;
			}
		}
	}

	protected String getLabelPrefix() {
		return labelPrefix;
	}

	protected String getLabelSuffix() {
		Runnable tempTask;
		synchronized (this) {
			tempTask = currentTask;
		}
		return "";
	}

	public void setLabelPrefix(String labelPrefix) {
		this.labelPrefix = labelPrefix;
	}

}
