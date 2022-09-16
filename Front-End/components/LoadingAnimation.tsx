import React from "react";

interface LoadingAnimationProps {}

export const LoadingAnimation: React.FC<LoadingAnimationProps> = ({}) => {
  return (
    <div className="animate-pulse">
      <div className="mx-auto my-3 w-full max-w-sm rounded-md border border-neutral-700 p-9 shadow-2xl">
        <div className="flex  space-x-4">
          <div className="h-10 w-10 rounded-full bg-zinc-900"></div>
          <div className="flex-1 space-y-6 py-5">
            <div className="h-2 rounded bg-slate-900"></div>
            <div className="space-y-3">
              <div className="grid grid-cols-3 gap-4">
                <div className="col-span-2 h-2 rounded bg-slate-900"></div>
                <div className="col-span-1 h-2 rounded bg-slate-900"></div>
              </div>
              <div className="h-2 rounded bg-slate-900"></div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};
