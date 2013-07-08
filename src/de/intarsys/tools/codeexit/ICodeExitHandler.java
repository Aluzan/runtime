/*
 * Copyright (c) 2007, intarsys consulting GmbH
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
package de.intarsys.tools.codeexit;

import de.intarsys.tools.functor.FunctorInvocationException;
import de.intarsys.tools.functor.IFunctor;
import de.intarsys.tools.functor.IFunctorCall;

/**
 * An "interpreter" object that can handle execution of the "source" with the
 * parameters in "args".
 * <p>
 * The implementation is called by a {@link CodeExit} that looks up a
 * {@link ICodeExitHandler} in the {@link ICodeExitHandlerRegistry} and
 * delegates execution of the source.
 * 
 */
public interface ICodeExitHandler {

	/**
	 * Perform the represented behavior. This is similar to the {@link IFunctor}
	 * interface, where the behavior itself is encapsulated. Here the definition
	 * of the behavior is provided by the {@link CodeExit} by some kind of
	 * source script, the {@link ICodeExitHandler} that fits the correct type
	 * will interpret it.
	 * 
	 * @param codeExit
	 *            The behavior definition
	 * @param call
	 *            The call
	 * @return The result of interpreting the {@link CodeExit} in the context of
	 *         <code>call</code>
	 * @throws FunctorInvocationException
	 */
	public Object perform(CodeExit codeExit, IFunctorCall call)
			throws FunctorInvocationException;

}
