/**
 * Copyright (c) 2016-2018 Zerocracy
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to read
 * the Software only. Permissions is hereby NOT GRANTED to use, copy, modify,
 * merge, publish, distribute, sublicense, and/or sell copies of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.zerocracy.bundles.modifies_wbs

import com.jcabi.github.Github
import com.jcabi.github.Issue
import com.jcabi.github.IssueLabels
import com.jcabi.xml.XML
import com.zerocracy.Farm
import com.zerocracy.Project
import com.zerocracy.entry.ExtGithub
import com.zerocracy.pm.scope.Wbs
import com.zerocracy.radars.github.Job

def exec(Project project, XML xml) {
  def wbs = new Wbs(project).bootstrap()
  String job = 'gh:test/test#1'
  assert wbs.exists(job)
  Farm farm = binding.variables.farm
  Github github = new ExtGithub(farm).value()
  Issue.Smart issue = new Issue.Smart(new Job.Issue(github, job))
  assert new IssueLabels.Smart(issue.labels()).contains('in')
}
